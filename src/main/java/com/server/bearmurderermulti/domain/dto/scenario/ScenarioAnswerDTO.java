package com.server.bearmurderermulti.domain.dto.scenario;

import com.server.bearmurderermulti.domain.dto.alibi.AlibiDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScenarioAnswerDTO {

    private String victim;
    private String crimeScene;
    private String method;
    private String witness;
    private String eyewitnessInformation;
    private String dailySummary;
    private List<AlibiDTO> alibis;
}
