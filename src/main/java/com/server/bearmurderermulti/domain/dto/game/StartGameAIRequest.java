package com.server.bearmurderermulti.domain.dto.game;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartGameAIRequest {

    private Long gameNo;
    private String language;

    public static StartGameAIRequest create(Long gameNo, String language) {
        return StartGameAIRequest.builder()
                .gameNo(gameNo)
                .language(language)
                .build();
    }
}
