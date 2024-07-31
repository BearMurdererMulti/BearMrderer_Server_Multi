package com.server.bearmurderermulti.service;

import com.server.bearmurderermulti.configuration.jwt.JwtProvider;
import com.server.bearmurderermulti.domain.dto.question.*;
import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.Member;
import com.server.bearmurderermulti.domain.entity.Question;
import com.server.bearmurderermulti.domain.entity.QuestionAnswer;
import com.server.bearmurderermulti.exception.AppException;
import com.server.bearmurderermulti.exception.ErrorCode;
import com.server.bearmurderermulti.repository.GameSetRepository;
import com.server.bearmurderermulti.repository.QuestionAnswerRepository;
import com.server.bearmurderermulti.repository.QuestionRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final GameSetRepository gameSetRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final JwtProvider jwtProvider;

    @Value("${ai.url}")
    private String aiUrl;

    public QuestionCreateResponse createQuestion(Member loginMember, QuestionCreateRequest request, HttpServletRequest httpServletRequest) {

        log.info("🐻Question Create 시작");

        String authHeader = httpServletRequest.getHeader("Authorization");

        // 요청에서 받은 Authorization 헤더 출력
        log.info("🐻Received Authorization header: {}", authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        // 토큰 부분만 출력
        String token = authHeader.substring(7);
        log.info("🐻Extracted token: {}", token);

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

        Optional<GameSet> optionalGameSet = gameSetRepository.findByGameSetNo(request.getGameSetNo());

        if (optionalGameSet.isEmpty()) {
            throw new AppException(ErrorCode.GAME_NOT_FOUND);
        }

        log.info("🐻user-npc Question Create unity 통신 완료");

        try {
            return sendAIServer(request);
        } catch (Exception e) {
            log.error("🐻AI 통신 실패 : ", e);
            throw e;
        }
    }

    public QuestionCreateResponse sendAIServer(QuestionCreateRequest request) {

        log.info("🐻Question Create AI 통신 시작");

        String aiServerUrl = aiUrl + "/api/v2/in-game/generate-questions";
        WebClient webClient = WebClient.builder().baseUrl(aiServerUrl).build();

        GameSet gameSet = gameSetRepository.findByGameSetNo(request.getGameSetNo())
                .orElseThrow(() -> new AppException(ErrorCode.GAME_SET_NOT_FOUND));

        // AI 서버에 보낼 요청 객체 생성
        AIQuestionCreateRequest aiQuestionSaveRequest = new AIQuestionCreateRequest();
        aiQuestionSaveRequest.setGameNo(request.getGameSetNo());
        aiQuestionSaveRequest.setNpcName(request.getNpcName());
        aiQuestionSaveRequest.setKeyWord(request.getKeyWord() != null ? request.getKeyWord() : "");
        aiQuestionSaveRequest.setKeyWordType(request.getKeyWordType() != null ? request.getKeyWordType() : "");

        // AI 서버로 요청
        QuestionCreateResponse response = webClient.post()
                .uri(aiServerUrl)
                .bodyValue(aiQuestionSaveRequest)
                .retrieve()
                .bodyToMono(QuestionCreateResponse.class)
                .onErrorResume(e -> {
                    log.error("🐻AI 통신 실패 : ", e);
                    throw new AppException(ErrorCode.AI_INTERNAL_SERVER_ERROR);
                })
                .block();

        List<QuestionCreateDTO> questions = response.getQuestions();
        for (QuestionCreateDTO saveDTO : questions) {
            Question question = QuestionCreateDTO.toEntity(saveDTO, gameSet, request);
            questionRepository.save(question);
        }

        log.info("🐻Question Create AI 통신 완료");

        return response;
    }


    public QuestionAnswerResponse getAnswer(Member loginMember, QuestionAnswerRequest request, HttpServletRequest httpServletRequest) {

        log.info("🐻Question Answer 시작");

        String authHeader = httpServletRequest.getHeader("Authorization");

        // 요청에서 받은 Authorization 헤더 출력
        log.info("🐻Received Authorization header: {}", authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        // 토큰 부분만 출력
        String token = authHeader.substring(7);
        log.info("🐻Extracted token: {}", token);

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

        Optional<GameSet> optionalGameSet = gameSetRepository.findByGameSetNo(request.getGameSetNo());

        if (optionalGameSet.isEmpty()) {
            throw new AppException(ErrorCode.GAME_NOT_FOUND);
        }

        log.info("🐻user-npc Question Answer unity 통신 완료");

        try {
            return sendAIToAnswer(request);
        } catch (Exception e) {
            log.error("🐻AI 통신 실패 : ", e);
            throw e;
        }
    }


    private QuestionAnswerResponse sendAIToAnswer(QuestionAnswerRequest request) {

        log.info("🐻Question Answer AI 통신 시작");

        String aiServerUrl = aiUrl + "/api/v2/in-game/generate-answer";
        WebClient webClient = WebClient.builder().baseUrl(aiServerUrl).build();

        GameSet gameSet = gameSetRepository.findByGameSetNo(request.getGameSetNo())
                .orElseThrow(() -> new AppException(ErrorCode.GAME_SET_NOT_FOUND));

        // AI 서버에 보낼 요청 객체 생성
        AIQuestionAnswerRequest aiQuestionAnswerRequest = new AIQuestionAnswerRequest();
        aiQuestionAnswerRequest.setGameNo(request.getGameSetNo());
        aiQuestionAnswerRequest.setNpcName(request.getNpcName());
        aiQuestionAnswerRequest.setQuestionIndex(request.getQuestionIndex());
        aiQuestionAnswerRequest.setKeyword(request.getKeyword() != null ? request.getKeyword() : "");
        aiQuestionAnswerRequest.setKeywordType(request.getKeywordType() != null ? request.getKeywordType() : "");

        // AI 서버로 요청
        QuestionAnswerResponse aiResponse = webClient.post()
                .uri(aiServerUrl)
                .bodyValue(aiQuestionAnswerRequest)
                .retrieve()
                .bodyToMono(QuestionAnswerResponse.class)
                .onErrorResume(e -> {
                    log.error("🐻AI 통신 실패 : ", e);
                    throw new AppException(ErrorCode.AI_INTERNAL_SERVER_ERROR);
                })
                .block();

        // 질문 조회
        Question question = questionRepository.findByGameSet_GameSetNoAndNpcNameAndQuestionIndex(
                request.getGameSetNo(), request.getNpcName(), request.getQuestionIndex())
                .orElseThrow(() -> new AppException(ErrorCode.QUESTION_NOT_FOUND));

        // 답변 저장
        QuestionAnswer questionAnswer = request.toEntity(question, aiResponse.getResponse());
        questionAnswerRepository.save(questionAnswer);

        log.info("🐻Question Answer AI 통신 완료");

        return aiResponse;
    }

}
