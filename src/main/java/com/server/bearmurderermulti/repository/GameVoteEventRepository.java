package com.server.bearmurderermulti.repository;

import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.GameVoteEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameVoteEventRepository extends JpaRepository<GameVoteEvent, Long> {

    Long countAllByGameSet(GameSet gameSet);
}
