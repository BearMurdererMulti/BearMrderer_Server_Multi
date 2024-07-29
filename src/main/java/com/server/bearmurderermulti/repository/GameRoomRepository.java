package com.server.bearmurderermulti.repository;

import com.server.bearmurderermulti.domain.entity.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {


}
