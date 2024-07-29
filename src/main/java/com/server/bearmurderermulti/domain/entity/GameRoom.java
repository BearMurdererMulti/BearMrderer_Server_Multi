package com.server.bearmurderermulti.domain.entity;

import com.server.bearmurderermulti.domain.enum_class.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "game_room_tb")
public class GameRoom extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_room_no")
    private Long gameRoomNo;

    private int roomNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_no", referencedColumnName = "member_no")
    private Member creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "participant_no", referencedColumnName = "member_no")
    private Member participant;

    @Enumerated(EnumType.STRING)
    private Role creatorRole;

    @Enumerated(EnumType.STRING)
    private Role participantRole;

}
