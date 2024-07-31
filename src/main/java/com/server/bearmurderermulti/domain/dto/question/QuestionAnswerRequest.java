package com.server.bearmurderermulti.domain.dto.question;

import com.server.bearmurderermulti.domain.entity.Question;
import com.server.bearmurderermulti.domain.entity.QuestionAnswer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionAnswerRequest {

    private Long gameSetNo;
    private String npcName;
    private Integer questionIndex;
    private String keyword;
    private String keywordType;

    public QuestionAnswer toEntity(Question question, String answerText) {
        return QuestionAnswer.builder()
                .question(question)
                .answerText(answerText)
                .build();
    }

}
