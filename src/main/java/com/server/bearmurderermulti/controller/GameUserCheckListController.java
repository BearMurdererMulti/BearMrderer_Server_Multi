package com.server.bearmurderermulti.controller;

import com.server.bearmurderermulti.domain.dto.gameUserCheckList.CheckListSaveRequest;
import com.server.bearmurderermulti.domain.dto.gameUserCheckList.CheckListSaveResponse;
import com.server.bearmurderermulti.exception.Response;
import com.server.bearmurderermulti.service.GameUserCheckListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/checklist")
@RequiredArgsConstructor
public class GameUserCheckListController {

    private final GameUserCheckListService gameUserCheckListService;

    @PostMapping
    public Response<List<CheckListSaveResponse>> saveCheckList(@RequestBody CheckListSaveRequest request) {

        List<CheckListSaveResponse> response = gameUserCheckListService.saveAndReturnCheckList(request);
        return Response.success(response);
    }
}