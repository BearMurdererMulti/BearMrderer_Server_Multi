package com.server.bearmurderermulti.repository;

import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.GameUserCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameUserCustomRepository extends JpaRepository<GameUserCustom, Long> {

    Optional<GameUserCustom> findByGameSet(GameSet gameSet);

}
