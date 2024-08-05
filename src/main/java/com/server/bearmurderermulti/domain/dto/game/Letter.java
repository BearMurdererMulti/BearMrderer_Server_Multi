package com.server.bearmurderermulti.domain.dto.game;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Letter {

    private String receiver;
    private String content;
    private String sender;

}
