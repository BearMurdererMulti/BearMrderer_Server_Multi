package com.server.bearmurderermulti.domain.dto.game;

import com.server.bearmurderermulti.domain.dto.gameNpcCustom.NpcCustomInfo;
import com.server.bearmurderermulti.domain.dto.gameUserCheckList.CheckListRequest;
import com.server.bearmurderermulti.domain.dto.gameUserCustom.GameUserCustomSaveRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SaveGameRequest {

    private Long gameSetNo;
    private int gameDay;
    private String voteNpcName;
    private boolean voteResult;
    private Long voteNightNumber;
    private List<CheckListRequest> checkList;
    private GameUserCustomSaveRequest userCustom;
    private List<NpcCustomInfo> npcCustomInfos;

}
