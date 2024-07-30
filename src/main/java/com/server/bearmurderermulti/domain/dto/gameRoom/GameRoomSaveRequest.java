package com.server.bearmurderermulti.domain.dto.gameRoom;

import com.server.bearmurderermulti.domain.entity.GameRoom;
import com.server.bearmurderermulti.domain.entity.Member;
import com.server.bearmurderermulti.domain.enum_class.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameRoomSaveRequest {

    private int roomNumber;
    private String creatorNickname;
    private String participantNickname;

    public GameRoom toEntity(Member creator, Member participant) {

        Role creatorRole = Role.CREATOR;
        Role participantRole = Role.PARTICIPANT;

        return GameRoom.builder()
                .roomNumber(this.roomNumber)
                .creator(creator)
                .participant(participant)
                .creatorRole(creatorRole)
                .participantRole(participantRole)
                .build();
    }

}
