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

@Service
@RequiredArgsConstructor
@Slf4j
public class GameRoomService {

    private final GameRoomRepository gameRoomRepository;
    private final MemberRepository memberRepository;

    public GameRoomSaveResponse createGameRoom(GameRoomSaveRequest request) {

        Member creator = memberRepository.findByNickname(request.getCreatorNickname())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_NICKNAME));

        Member participant = memberRepository.findByNickname(request.getParticipantNickname())
                .orElseThrow(() -> new AppException(ErrorCode.INVALID_NICKNAME));

        GameRoom gameRoom = request.toEntity(creator, participant);
        GameRoom savedGameRoom = gameRoomRepository.save(gameRoom);

        return new GameRoomSaveResponse(savedGameRoom);

    }




}
