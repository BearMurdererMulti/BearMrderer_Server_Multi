package com.server.bearmurderermulti.domain.dto.gameUserCheckList;

import com.server.bearmurderermulti.domain.entity.GameNpc;
import com.server.bearmurderermulti.domain.entity.GameUserCheckList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckListRequest {

    private String npcName;
    private String mark;
    private String checkJob;

    public GameUserCheckList toEntity(GameNpc gameNpc) {
        return GameUserCheckList.builder()
                .mark(mark)
                .checkJob(checkJob)
                .gameNpc(gameNpc)
                .build();
    }

}