package com.server.bearmurderermulti.domain.dto.question;

import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.Question;
import com.server.bearmurderermulti.domain.enum_class.KeyWordType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionCreateDTO {

    private Integer number;
    private String question;

    public static Question toEntity(QuestionCreateDTO dto, GameSet gameSet, QuestionCreateRequest request) {
        return Question.builder()
                .npcName(request.getNpcName())
                .keyWord(request.getKeyWord())
                .keyWordType(KeyWordType.valueOf(request.getKeyWordType().toUpperCase()))
                .gameSet(gameSet)
                .questionIndex(dto.getNumber())
                .questionText(dto.getQuestion())
                .build();
    }

}
