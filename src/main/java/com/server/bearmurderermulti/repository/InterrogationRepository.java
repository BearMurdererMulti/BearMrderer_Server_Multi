package com.server.bearmurderermulti.repository;

import com.server.bearmurderermulti.domain.entity.Interrogation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterrogationRepository extends JpaRepository<Interrogation, Long> {

}
