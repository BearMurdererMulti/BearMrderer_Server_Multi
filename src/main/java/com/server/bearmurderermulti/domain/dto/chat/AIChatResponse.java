package com.server.bearmurderermulti.domain.dto.chat;

import com.server.bearmurderermulti.domain.dto.scenario.TokensDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIChatResponse {

    private ChatAnswerDTO answer;
    private TokensDTO tokens;

}

