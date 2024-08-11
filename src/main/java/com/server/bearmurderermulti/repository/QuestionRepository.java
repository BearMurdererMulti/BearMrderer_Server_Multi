package com.server.bearmurderermulti.repository;

import com.server.bearmurderermulti.domain.entity.GameSet;
import com.server.bearmurderermulti.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findByGameSet_GameSetNoAndNpcNameAndQuestionIndex(Long gameSetNo, String npcName, Integer questionIndex);

    // 가장 최근의 질문을 찾게끔
    Optional<Question> findTopByGameSet_GameSetNoAndNpcNameAndQuestionIndexOrderByCreatedAtDesc(Long gameSetNo, String npcName, Integer questionIndex);
}
