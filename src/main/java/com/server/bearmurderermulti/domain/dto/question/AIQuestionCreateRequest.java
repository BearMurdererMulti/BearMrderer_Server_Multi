package com.server.bearmurderermulti.domain.dto.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIQuestionCreateRequest {

    private int gameNo;

    @JsonProperty("npc_name")
    private String npcName;

    private String keyWord;

    @JsonProperty("keyword_type")
    private String keyWordType;

}
