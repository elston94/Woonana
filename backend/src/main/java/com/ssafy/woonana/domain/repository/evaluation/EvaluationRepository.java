package com.ssafy.woonana.domain.repository.evaluation;

import com.ssafy.woonana.domain.model.dto.user.response.UserEvaluateResponse;
import com.ssafy.woonana.domain.model.entity.evaluation.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    @Query(value = "select e from Evaluation e join fetch e.board where e.evaluationUser.userId=:userId")
    List<Evaluation> findEvaluationsByEvaluationUser(Long userId);

    @Query(value="select avg(e.evaluation_rating_score) from evaluation e where evaluation_target_id=:userId", nativeQuery = true)
    int calAvgScore(Long userId);

    @Query("select count(e.evaluationId) from Evaluation e " +
            "where e.evaluationUser.userId=:loginId " +
            "and e.evaluationTarget.userId=:userId and e.board.id=:boardId ")
    int findParticipationsByUsersAndBoard(@Param("loginId") Long loginId, @Param("userId") Long userId, @Param("boardId") Long boardId);

    @Modifying(clearAutomatically = true)
    @Query("delete from Evaluation e where e.board.id = :boardId")
    void deleteEvaluationByBoardId(@Param("boardId") Long boardId);
}
