package com.server.bearmurderermulti.domain.dto.interrogation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterrogationProceedRequest {

    private Long gameSetNo;
    private String npcName;
    private String content;

}
