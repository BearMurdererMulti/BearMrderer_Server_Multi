package com.server.bearmurderermulti.domain.dto.scenario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AiMakeScenarioResponse {

    private ScenarioAnswerDTO answer;
    private TokensDTO tokens;

}
