package com.server.bearmurderermulti.repository;

import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.Interrogation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterrogationRepository extends JpaRepository<Interrogation, Long> {

    Optional<Interrogation> findByGameSetAndNpcName(GameSet gameSet, String npcName);

}
