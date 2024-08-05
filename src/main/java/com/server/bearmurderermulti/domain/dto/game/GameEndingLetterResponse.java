package com.server.bearmurderermulti.domain.dto.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameEndingLetterResponse {

    private String result;
    private Letter chiefLetter;
    private Letter murdererLetter;
    private List<SurvivorLetter> survivorsLetters;


}
