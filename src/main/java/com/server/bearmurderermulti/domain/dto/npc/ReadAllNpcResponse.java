package com.server.bearmurderermulti.domain.dto.npc;

import com.server.bearmurderermulti.domain.entity.Npc;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReadAllNpcResponse {
    private Long npcNo;
    private String npcName;
    private String npcPersonality;
    private String npcFeature;
    private LocalDateTime npcCreatedAt;
    private LocalDateTime npcLastModifiedAt;
    private LocalDateTime npcDeletedAt;

    public static ReadAllNpcResponse of(Npc npc) {
        return ReadAllNpcResponse.builder()
                .npcNo(npc.getNpcNo())
                .npcName(npc.getNpcName())
                .npcPersonality(npc.getNpcPersonality())
                .npcFeature(npc.getNpcFeature())
                .npcCreatedAt(npc.getCreatedAt())
                .npcLastModifiedAt(npc.getLastModifiedAt())
                .npcDeletedAt(npc.getDeletedAt())
                .build();
    }
}
