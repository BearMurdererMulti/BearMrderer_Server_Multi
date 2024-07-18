package com.server.bearmurderermulti.service;

import com.server.bearmurderermulti.domain.dto.gameUserCheckList.CheckListRequest;
import com.server.bearmurderermulti.domain.dto.gameUserCheckList.CheckListSaveRequest;
import com.server.bearmurderermulti.domain.dto.gameUserCheckList.CheckListSaveResponse;
import com.server.bearmurderermulti.domain.entity.GameNpc;
import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.GameUserCheckList;
import com.server.bearmurderermulti.exception.AppException;
import com.server.bearmurderermulti.exception.ErrorCode;
import com.server.bearmurderermulti.repository.GameNpcRepository;
import com.server.bearmurderermulti.repository.GameSetRepository;
import com.server.bearmurderermulti.repository.GameUserCheckListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GameUserCheckListService {

    private final GameUserCheckListRepository gameUserChecklistRepository;
    private final GameNpcRepository gameNpcRepository;
    private final GameSetRepository gameSetRepository;

    public List<CheckListSaveResponse> saveAndReturnCheckList(CheckListSaveRequest request) {

        GameSet gameSet = gameSetRepository.findByGameSetNo(request.getGameSetNo())
                .orElseThrow(() -> new AppException(ErrorCode.GAME_SET_NOT_FOUND));

        List<CheckListSaveResponse> responses = new ArrayList<>();

        for (CheckListRequest checkListRequest : request.getCheckList()) {

            GameNpc gameNpc = gameNpcRepository.findByNpcNameAndGameSet(checkListRequest.getNpcName(), gameSet)
                    .orElseThrow(() -> new AppException(ErrorCode.NPC_NOT_FOUND));

            GameUserCheckList gameUserCheckList = checkListRequest.toEntity(gameNpc);

            gameUserCheckList = gameUserChecklistRepository.save(gameUserCheckList);

            responses.add(CheckListSaveResponse.of(gameUserCheckList));

        }
        return responses;
    }
}