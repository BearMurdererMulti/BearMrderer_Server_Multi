package com.server.bearmurderermulti.domain.dto.member;

import com.server.bearmurderermulti.domain.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReadAllMemberResponse {

    private Long memberNo;
    private String account;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private LocalDateTime deletedAt;

    public static ReadAllMemberResponse of(Member member) {
        return ReadAllMemberResponse.builder()
                .memberNo(member.getMemberNo())
                .account(member.getAccount())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .lastModifiedAt(member.getLastModifiedAt())
                .deletedAt(member.getDeletedAt())
                .build();
    }
}
