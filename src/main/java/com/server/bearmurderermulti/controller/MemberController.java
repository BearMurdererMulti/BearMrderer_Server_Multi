package com.server.bearmurderermulti.controller;

import com.server.bearmurderermulti.domain.dto.member.ReadAllMemberResponse;
import com.server.bearmurderermulti.domain.dto.member.ReadMemberResponse;
import com.server.bearmurderermulti.exception.Response;
import com.server.bearmurderermulti.service.CustomUserDetails;
import com.server.bearmurderermulti.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/{memberNo}")
    public ResponseEntity<Response<ReadMemberResponse>> readByMemberNickname(@PathVariable long memberNo) {

        ReadMemberResponse readMemberResponse = memberService.readByNo(memberNo);

        return ResponseEntity.ok(Response.success(readMemberResponse));
    }

    @GetMapping("/all")
    public ResponseEntity<Response<Page<ReadAllMemberResponse>>> readAll(@AuthenticationPrincipal CustomUserDetails userDetails) {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("memberNo").descending());

        Page<ReadAllMemberResponse> readAllMemberResponsePage = memberService.readAllMember(pageable);

        return ResponseEntity.ok(Response.success(readAllMemberResponsePage));
    }

}