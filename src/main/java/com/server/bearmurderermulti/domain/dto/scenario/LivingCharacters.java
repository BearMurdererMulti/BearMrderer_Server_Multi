package com.server.bearmurderermulti.domain.dto.scenario;

import com.server.bearmurderermulti.domain.enum_class.NpcStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivingCharacters {

    private String name;
    private String job;
    private NpcStatus status;

}
