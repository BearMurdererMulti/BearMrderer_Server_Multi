package com.server.bearmurderermulti.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.server.bearmurderermulti.domain.dto.interrogation.InterrogationStartRequest;
import com.server.bearmurderermulti.domain.dto.interrogation.InterrogationStartResponse;
import com.server.bearmurderermulti.domain.entity.Member;
import com.server.bearmurderermulti.exception.Response;
import com.server.bearmurderermulti.service.CustomUserDetails;
import com.server.bearmurderermulti.service.InterrogationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/interrogation")
@RequiredArgsConstructor
@Slf4j
public class InterrogationController {

    private final InterrogationService interrogationService;

    @PostMapping("/start")
    public Response<InterrogationStartResponse> startInterrogation(InterrogationStartRequest request, @AuthenticationPrincipal CustomUserDetails userDetails, HttpServletRequest httpServletRequest) throws JsonProcessingException {

        Member loginMember = userDetails.getMember();

        InterrogationStartResponse response = interrogationService.interrogationStart(request, loginMember, httpServletRequest);
        return Response.success(response);
    }

}
