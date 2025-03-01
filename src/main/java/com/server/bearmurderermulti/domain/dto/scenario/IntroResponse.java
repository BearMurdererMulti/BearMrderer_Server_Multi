package com.server.bearmurderermulti.domain.dto.scenario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IntroResponse {

    private IntroAnswerDTO answer;
    private TokensDTO tokens;

}
