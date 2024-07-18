package com.server.bearmurderermulti.domain.dto.gameNpcCustom;

import com.server.bearmurderermulti.domain.entity.GameNpc;
import com.server.bearmurderermulti.domain.entity.GameNpcCustom;
import com.server.bearmurderermulti.domain.entity.GameSet;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NpcCustomInfo {

    private String npcName;
    private int mouth;
    private int ear;
    private int body;
    private int tail;

    public GameNpcCustom toEntity(GameSet gameSet, GameNpc gameNpc) {
        return GameNpcCustom.builder()
                .gameSet(gameSet)
                .gameNpc(gameNpc)
                .mouth(mouth)
                .ear(ear)
                .body(body)
                .tail(tail)
                .build();

    }

}
