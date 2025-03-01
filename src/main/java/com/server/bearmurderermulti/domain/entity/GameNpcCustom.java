package com.server.bearmurderermulti.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "game_npc_custom_tb")
public class GameNpcCustom extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_npc_custom_no")
    private Long GameNpcCustomNo;

    @Column(name = "mouth")
    private int mouth;

    @Column(name = "ear")
    private int ear;

    @Column(name = "body")
    private int body;

    @Column(name = "tail")
    private int tail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_set_no")
    private GameSet gameSet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_npc_no")
    private GameNpc gameNpc;

}
