package com.server.bearmurderermulti.controller;

import com.server.bearmurderermulti.domain.dto.gameUserCustom.GameUserCustomSaveRequest;
import com.server.bearmurderermulti.domain.dto.gameUserCustom.GameUserCustomSaveResponse;
import com.server.bearmurderermulti.domain.entity.Member;
import com.server.bearmurderermulti.exception.Response;
import com.server.bearmurderermulti.service.CustomUserDetails;
import com.server.bearmurderermulti.service.GameUserCustomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/custom")
@RequiredArgsConstructor
public class GameUserCustomController {

    private final GameUserCustomService gameUserCustomService;

    @PostMapping("/save")
    public Response<GameUserCustomSaveResponse> customSave(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody GameUserCustomSaveRequest request) {

        Member loginMember = customUserDetails.getMember();
        GameUserCustomSaveResponse response = gameUserCustomService.saveCustom(loginMember, request);
        return Response.success(response);
    }
}
