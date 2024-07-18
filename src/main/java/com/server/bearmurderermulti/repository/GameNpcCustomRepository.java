package com.server.bearmurderermulti.repository;

import com.server.bearmurderermulti.domain.entity.GameNpc;
import com.server.bearmurderermulti.domain.entity.GameNpcCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameNpcCustomRepository extends JpaRepository<GameNpcCustom, Long> {

    Optional<GameNpcCustom> findByGameNpc(GameNpc gameNpc);
}
