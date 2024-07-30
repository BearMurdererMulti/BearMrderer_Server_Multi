package com.server.bearmurderermulti.service;

import com.server.bearmurderermulti.domain.dto.gameRoom.GameRoomSaveRequest;
import com.server.bearmurderermulti.domain.dto.gameRoom.GameRoomSaveResponse;
import com.server.bearmurderermulti.domain.entity.GameRoom;
import com.server.bearmurderermulti.domain.entity.Member;
import com.server.bearmurderermulti.exception.AppException;
import com.server.bearmurderermulti.exception.ErrorCode;
import com.server.bearmurderermulti.repository.GameRoomRepository;
import com.server.bearmurderermulti.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GameRoomService {

    private final GameRoomRepository gameRoomRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public GameRoomSaveResponse createGameRoom(GameRoomSaveRequest request) {

        log.info("🐻GameRoom 저장 시작");

        log.info("🐻Request Data: {}", request);

        Member creator = memberRepository.findByNickname(request.getCreatorNickname())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_NICKNAME));

        Member participant = memberRepository.findByNickname(request.getParticipantNickname())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_NICKNAME));

        log.info("🐻Creator found: {}", creator.getNickname());
        log.info("🐻Participant found: {}", participant.getNickname());

        GameRoom gameRoom = request.toEntity(creator, participant);
        GameRoom savedGameRoom = gameRoomRepository.save(gameRoom);

        log.info("🐻GameRoom 저장 완료");

        return new GameRoomSaveResponse(savedGameRoom);

    }




}
