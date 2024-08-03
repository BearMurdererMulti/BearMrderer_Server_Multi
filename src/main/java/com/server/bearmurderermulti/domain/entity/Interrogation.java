package com.server.bearmurderermulti.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "interrogation")
public class Interrogation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interrogation_no")
    private Long interrogationNo;

    private String npcName;

    private String weapon;

    private String userQuestion;

    private String answer;

    private Integer heartRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_set_no")
    private GameSet gameSet;




}
