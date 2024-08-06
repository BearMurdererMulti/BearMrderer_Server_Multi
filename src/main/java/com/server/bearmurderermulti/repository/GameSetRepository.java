package com.server.bearmurderermulti.repository;

import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GameSetRepository extends JpaRepository<GameSet, Long> {

    Optional<GameSet> findByGameSetNo(Long gameSetNo);

    @Query("SELECT gs FROM GameSet gs WHERE gs.gameSetNo = :gameSetNo AND (gs.host = :member OR gs.participant = :member)")
    Optional<GameSet> findByGameSetNoAndMember(@Param("gameSetNo") Long gameSetNo, @Param("member") Member member);

    @Query("SELECT gs FROM GameSet gs JOIN FETCH gs.host h LEFT JOIN FETCH gs.participant p WHERE (h = :member OR p = :member) AND gs.gameStatus <> 'GAME_END'")
    List<GameSet> findGameSetsByMember(@Param("member") Member member);

    @Query("SELECT gs FROM GameSet gs JOIN FETCH gs.host h LEFT JOIN FETCH gs.participant p WHERE (h = :member OR p = :member) AND gs.gameSetNo = :gameSetNo AND gs.gameStatus = 'GAME_END'")
    Optional<GameSet> findEndedGameSetByMemberAndGameSetNo(@Param("gameSetNo") Long gameSetNo, @Param("member") Member member);



}
