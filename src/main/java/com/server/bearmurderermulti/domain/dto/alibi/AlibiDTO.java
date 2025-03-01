package com.server.bearmurderermulti.domain.dto.alibi;

import com.server.bearmurderermulti.domain.entity.GameAlibi;
import com.server.bearmurderermulti.domain.entity.GameNpc;
import com.server.bearmurderermulti.domain.entity.GameScenario;
import com.server.bearmurderermulti.exception.AppException;
import com.server.bearmurderermulti.exception.ErrorCode;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AlibiDTO {

    private String name;
    private String alibi;
    private Long gameNpcNo;

    public GameAlibi toEntity(GameScenario gameScenario, GameNpc gameNpc) {
        if (gameNpc == null) {
            throw new AppException(ErrorCode.NPC_NOT_FOUND);  // Handle missing NPC
        }
        return GameAlibi.builder()
                .alibi(alibi)
                .gameScenario(gameScenario)
                .gameNpc(gameNpc)
                .build();
    }

    public static AlibiDTO of(GameAlibi gameAlibi) {
        AlibiDTO alibiDTO = new AlibiDTO();
        alibiDTO.name = gameAlibi.getGameNpc().getNpcName();
        alibiDTO.alibi = gameAlibi.getAlibi();
        alibiDTO.gameNpcNo = gameAlibi.getGameNpc().getGameNpcNo();
        return alibiDTO;
    }
}
