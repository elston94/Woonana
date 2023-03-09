package com.ssafy.woonana.domain.repository.participation;

import com.ssafy.woonana.domain.model.dto.user.response.UserParticipateLog;
import com.ssafy.woonana.domain.model.dto.user.response.UserParticipatedCheck;
import com.ssafy.woonana.domain.model.entity.board.Board;
import com.ssafy.woonana.domain.model.entity.participation.Participation;
import com.ssafy.woonana.domain.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ParticipationRepository extends JpaRepository<Participation, Long> {

    @Query(value = "select p from Participation p where p.user.userId=:userId and p.allowed=true order by p.board.meetStartDate desc")
    List<Participation> findParticipationsByUser(Long userId); // user에 해당하는 참여들 리턴

    @Query("select p from Participation p where p.board.id = :boardId and p.allowed = false")
    List<Participation> findParticipationByBoardId(@Param("boardId") Long boardId);

    @Query("select p from Participation p where p.board.id = :boardId and p.user.userId = :userId")
    Participation findParticipation(@Param("boardId") Long boardId, @Param("userId") Long userId);

    @Query("select b.id as boardId, p.id as participationId, b.meetStartDate as meetStartDate, (select e.name from Exercise e where e.id = b.exercise.id) as exerciseName " +
    "from Participation p " +
    "join Board b on p.board.id = b.id " +
    "where p.user.userId = :userId and p.participatedCheck = false")
    List<UserParticipateLog> findParticipationsByUserId(@Param("userId") Long userId);

    @Query("select b.id as boardId, p.id as participationId " +
    "from Participation p " +
    "join Board b on p.board.id = b.id " +
    "where p.user.userId = :userId and p.allowed = true " +
    "and (:startDate between b.meetStartDate and b.meetEndDate " +
    "or :endDate between b.meetStartDate and b.meetEndDate)")
    List<UserParticipatedCheck> findParticipationsByTime(@Param("userId") Long userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("select b from Board b where b.id in (select p.board.id from Participation p where p.user.userId=:userId) order by b.id desc ")
    List<Board> findBoardByParticipation(@Param("userId") Long userId);

    @Query("select p from Participation p join fetch p.user where p.board.id=:boardId")
    List<Participation> findParticipationsByBoardId(@Param("boardId") Long boardId); //  boardId로 participation 테이블 조회

    @Modifying(clearAutomatically = true)
    @Query("delete from Participation p where p.board.id = :boardId")
    void deleteParticipationByBoardId(@Param("boardId") Long boardId);

    @Query("select p.board.id from Participation p where p.user.userId=:userId and p.allowed=true")
    List<Long> participateBoardList(Long userId);
}
