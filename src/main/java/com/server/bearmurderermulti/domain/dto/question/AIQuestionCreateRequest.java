package com.server.bearmurderermulti.domain.dto.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIQuestionCreateRequest {

    private Long gameNo;
    private String npcName;
    private String keyWord;
    private String keyWordType;

    public static AIQuestionCreateRequest from(QuestionCreateRequest request) {
        return new AIQuestionCreateRequest(
                request.getGameSetNo(),
                request.getNpcName(),
                request.getKeyWord(),
                request.getKeyWordType()
        );
    }
}
