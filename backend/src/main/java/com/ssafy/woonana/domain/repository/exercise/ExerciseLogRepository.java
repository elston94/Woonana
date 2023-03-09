package com.ssafy.woonana.domain.repository.exercise;

import com.ssafy.woonana.domain.model.dto.exercise.response.ExerciseLogCountResponse;
import com.ssafy.woonana.domain.model.dto.exercise.response.ExerciseLog;
import com.ssafy.woonana.domain.model.dto.exercise.response.ExerciseLogDate;
import com.ssafy.woonana.domain.model.dto.exercise.response.ExerciseLogResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseLogRepository extends JpaRepository<com.ssafy.woonana.domain.model.entity.exercise.ExerciseLog, Long> {

    @Query("select count(el.exercise_log_id) from ExerciseLog el where el.user.userId=:userId")
    int countExerciseLogsByUser(Long userId);

    @Query(value = "select el.exercise.id as exerciseId, count(el.exercise_log_id) as exerciseCount from ExerciseLog el " +
            "where el.board.meetEndDate >= :monthStartDate " +
            "and el.board.meetEndDate < :monthEndDate " +
            "and el.user.userId=:userId " +
            "group by el.exercise.id")
    List<ExerciseLogResponse> findExerciseLogByMonth(@Param("userId") Long userId, LocalDateTime monthStartDate, LocalDateTime monthEndDate);

    @Query("select el.exercise.id as exerciseId, e.name as exerciseName, count(el.exercise.id) as exerciseCount from ExerciseLog el " +
            "join Exercise e on el.exercise.id = e.id where el.user.userId = :userId group by el.exercise.id ")
    List<ExerciseLog> findExerciseLogByUserId(@Param("userId")Long userId);

    @Query("select el.exercise.id as exerciseId, function('date_format', b.meetEndDate, '%Y-%m-%d') as endDate from ExerciseLog el " +
            "join Board b on el.user.userId = b.user.userId where el.user.userId = :userId ")
    List<ExerciseLogDate> findExerciseLogByLikes(@Param("userId")Long userId);

    @Query(value="select el.exercise_id, count(*) as exerciseCount from exercise_log el " +
            "where exercise_id=:exerciseId and el.user_id=:userId " +
            "group by el.exercise_id", nativeQuery = true)
    List<ExerciseLogCountResponse> findExerciseCountByUser(@Param("exerciseId") Long exerciseId, @Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query("delete from ExerciseLog e where e.board.id = :boardId")
    void deleteLogByBoardId(@Param("boardId") Long boardId);
}
