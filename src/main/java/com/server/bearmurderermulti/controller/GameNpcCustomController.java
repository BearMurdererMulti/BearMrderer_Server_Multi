package com.server.bearmurderermulti.controller;

import com.server.bearmurderermulti.domain.dto.gameNpcCustom.GameNpcCustomSaveRequest;
import com.server.bearmurderermulti.domain.dto.gameNpcCustom.GameNpcCustomSaveResponse;
import com.server.bearmurderermulti.exception.Response;
import com.server.bearmurderermulti.service.GameNpcCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/npc/custom")
@RequiredArgsConstructor
public class GameNpcCustomController {

    private final GameNpcCustomService gameNpcCustomService;

    @PostMapping("/save")
    public Response<GameNpcCustomSaveResponse> saveNpcCustom(@RequestBody GameNpcCustomSaveRequest request) {

        GameNpcCustomSaveResponse response = gameNpcCustomService.npcCustomSave(request);

        return Response.success(response);
    }
}
