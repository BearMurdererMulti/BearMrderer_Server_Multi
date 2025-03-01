package com.server.bearmurderermulti.domain.dto.game;

import com.server.bearmurderermulti.domain.dto.alibi.AlibiDTO;
import com.server.bearmurderermulti.domain.dto.gameNpcCustom.NpcCustomInfo;
import com.server.bearmurderermulti.domain.dto.gameUserCheckList.CheckListSaveResponse;
import com.server.bearmurderermulti.domain.dto.scenario.MakeScenarioResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoadGameResponse {

    private LoginGameSetDTO gameSet;
    private String deadNpc;
    private String deadPlace;
    private List<CheckListSaveResponse> checkList;
    private List<AlibiDTO> alibi;
    private MakeScenarioResponse scenario; // scenario, npcList
    private List<NpcCustomInfo> npcCustomInfos;

    public static LoadGameResponse of(LoginGameSetDTO gameSet, String deadNpc, String deadPlace,
                                      List<CheckListSaveResponse> checkList, List<AlibiDTO> alibi,
                                      MakeScenarioResponse scenario, List<NpcCustomInfo> npcCustomInfos) {
        return new LoadGameResponse(gameSet, deadNpc, deadPlace, checkList, alibi, scenario, npcCustomInfos);
    }

}
