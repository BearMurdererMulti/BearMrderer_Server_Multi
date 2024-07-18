package com.server.bearmurderermulti.repository;

import com.server.bearmurderermulti.domain.entity.GameAlibi;
import com.server.bearmurderermulti.domain.entity.GameNpc;
import com.server.bearmurderermulti.domain.entity.GameScenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GameAlibiRepository extends JpaRepository<GameAlibi, Long> {

    Optional<GameAlibi> findByGameScenarioAndGameNpc(GameScenario gameScenario, GameNpc gameNpc);
    List<GameAlibi> findByGameNpc(GameNpc gameNpc);

}
