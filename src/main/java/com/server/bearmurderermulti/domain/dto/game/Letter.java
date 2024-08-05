package com.server.bearmurderermulti.domain.dto.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Letter {

    private String receiver;
    private String content;
    private String sender;

}
