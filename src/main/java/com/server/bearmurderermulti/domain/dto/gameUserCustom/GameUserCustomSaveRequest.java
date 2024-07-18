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

    private String head;
    private String eye;
    private String mouth;
    private String ear;
    private String body;
    private String tail;
    private Long gameSetNo;

    public GameUserCustom toEntity(GameUserCustomSaveRequest request, GameSet gameSet) {
        return GameUserCustom.builder()
                .head(request.head)
                .eye(request.eye)
                .mouth(request.mouth)
                .ear(request.ear)
                .body(request.body)
                .tail(request.tail)
                .gameSet(gameSet)
                .build();
    }
}
