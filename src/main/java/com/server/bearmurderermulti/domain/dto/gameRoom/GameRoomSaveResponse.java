package com.server.bearmurderermulti.domain.dto.gameRoom;

import com.server.bearmurderermulti.domain.entity.GameRoom;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameRoomSaveResponse {

    private boolean isSaved; // 저장 완료 여부
    private LocalDateTime saveTime; // 저장 시간

    public GameRoomSaveResponse(GameRoom gameRoom) {
        this.isSaved = true;
        this.saveTime = gameRoom.getCreatedAt();
    }

}
