package com.server.bearmurderermulti.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.bearmurderermulti.configuration.jwt.JwtProvider;
import com.server.bearmurderermulti.domain.dto.interrogation.InterrogationStartRequest;
import com.server.bearmurderermulti.domain.dto.interrogation.InterrogationStartResponse;
import com.server.bearmurderermulti.domain.dto.scenario.AiMakeScenarioResponse;
import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.Interrogation;
import com.server.bearmurderermulti.domain.entity.Member;
import com.server.bearmurderermulti.exception.AppException;
import com.server.bearmurderermulti.exception.ErrorCode;
import com.server.bearmurderermulti.repository.GameSetRepository;
import com.server.bearmurderermulti.repository.InterrogationRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class InterrogationService {

    private final InterrogationRepository interrogationRepository;
    private final GameSetRepository gameSetRepository;
    private final JwtProvider jwtProvider;

    @Value("${ai.url}")
    private String aiUrl;

    public InterrogationStartResponse interrogationStart(InterrogationStartRequest request, Member loginMember, HttpServletRequest httpServletRequest) throws JsonProcessingException {

        log.info("🐻Interrogation Start 시작");

        String authHeader = httpServletRequest.getHeader("Authorization");

        if (loginMember == null) {
            log.error("🐻loginMember is null");
            throw new AppException(ErrorCode.UNAUTHORIZED);
        } else {
            if (loginMember.getNickname() == null) {
                log.error("🐻loginMember nickname is null");
                throw new AppException(ErrorCode.UNAUTHORIZED);
            }
        }

        // 토큰 유효성 검사 결과 출력
        boolean isValid = jwtProvider.validateToken(authHeader);
        log.info("🐻Token validation result: {}", isValid);

        if (!isValid) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        GameSet gameSet = gameSetRepository.findByGameSetNo(request.getGameSetNo())
                .orElseThrow(() -> new AppException(ErrorCode.GAME_SET_NOT_FOUND));

        String aiServerUrl =  aiUrl + "/api/v2/interrogation/new";
        WebClient webClient = WebClient.builder().baseUrl(aiServerUrl).build();


        Map<String, Object> requestData = new HashMap<>();
        requestData.put("gameNo", request.getGameSetNo());
        requestData.put("npcName", request.getNpcName());
        requestData.put("weapon", request.getWeapon());

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonRequest = objectMapper.writeValueAsString(requestData);
        log.info("🐻jsonRequest : {}", jsonRequest);

        InterrogationStartResponse response = webClient
                .post()
                .uri(aiServerUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(jsonRequest))
                .retrieve()
                .bodyToMono(InterrogationStartResponse.class)
                .block();

        Interrogation interrogation = request.toEntity(gameSet);
        interrogationRepository.save(interrogation);

        return response;
    }

}
