package com.server.bearmurderermulti.domain.dto.gameUserCustom;

import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.GameUserCustom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameUserCustomSaveRequest {

    private int eyes;
    private int mouth;
    private int ears;
    private int body;
    private int tail;
    private Long gameSetNo;

    public GameUserCustom toEntity(GameUserCustomSaveRequest request, GameSet gameSet) {
        return GameUserCustom.builder()
                .eyes(request.eyes)
                .mouth(request.mouth)
                .ears(request.ears)
                .body(request.body)
                .tail(request.tail)
                .gameSet(gameSet)
                .build();
    }
}
