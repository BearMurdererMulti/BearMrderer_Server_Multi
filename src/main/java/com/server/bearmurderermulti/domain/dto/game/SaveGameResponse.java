package com.server.bearmurderermulti.domain.dto.game;

import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.enum_class.MafiaArrest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveGameResponse {

    private String mafiaArrest;
    private boolean isSaved; // 저장 완료 여부
    private LocalDateTime saveTime; // 저장 시간
    private int saveDay; // 저장 날짜

    public SaveGameResponse(GameSet gameSet, MafiaArrest mafiaArrest) {
        this.isSaved = true;
        this.saveTime = gameSet.getLastModifiedAt();
        this.saveDay = gameSet.getGameDay();
        this.mafiaArrest = mafiaArrest.name();
    }
}
