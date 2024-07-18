package com.server.bearmurderermulti.service;

import com.server.bearmurderermulti.domain.dto.gameUserCustom.GameUserCustomSaveRequest;
import com.server.bearmurderermulti.domain.dto.gameUserCustom.GameUserCustomSaveResponse;
import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.GameUserCustom;
import com.server.bearmurderermulti.domain.entity.Member;
import com.server.bearmurderermulti.exception.AppException;
import com.server.bearmurderermulti.exception.ErrorCode;
import com.server.bearmurderermulti.repository.GameSetRepository;
import com.server.bearmurderermulti.repository.GameUserCustomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameUserCustomService {

    private final GameSetRepository gameSetRepository;
    private final GameUserCustomRepository gameUserCustomRepository;

    // userCustom save
    @Transactional
    public GameUserCustomSaveResponse saveCustom(Member loginMember, GameUserCustomSaveRequest request) {

        log.info("🐻user character custom 저장 시작");

        GameSet gameSet = gameSetRepository.findByGameSetNoAndMember(request.getGameSetNo(), loginMember)
                .orElseThrow(() -> new AppException(ErrorCode.GAME_SET_NOT_FOUND));

        GameUserCustom gameUserCustom = request.toEntity(request, gameSet);

        gameUserCustomRepository.save(gameUserCustom);

        log.info("🐻user character custom 저장 완료");

        return new GameUserCustomSaveResponse(gameUserCustom);
    }
}
