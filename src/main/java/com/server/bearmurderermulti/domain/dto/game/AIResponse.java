package com.server.bearmurderermulti.domain.dto.game;

import com.server.bearmurderermulti.domain.entity.GameScenario;
import com.server.bearmurderermulti.domain.entity.GameSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIResponse {

    private Answer answer;

    public GameScenario toEntity(GameSet gameSet) {
        return GameScenario.builder()
                .victim(this.answer.getVictim())
                .crimeScene(this.answer.getCrimeScene())
                .method(this.answer.getMethod())
                .witness("")
                .eyewitnessInformation("")
                .dailySummary("")
                .scenarioPromptToken(0)
                .scenarioCompletionToken(0)
                .gameSet(gameSet)
                .build();
    }

}
