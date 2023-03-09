package com.ssafy.woonana.domain.repository.board;

import com.ssafy.woonana.domain.model.dto.board.request.BoardUpdateRequest;
import com.ssafy.woonana.domain.model.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreatedDateDesc();

    @Query("select b from Board b where b.user.userId = :userId order by b.createdDate desc")
    List<Board> findBoardsByUserId(@Param("userId") Long userId);

    @Query("select b from Board b where b.exercise.id = :exerciseId")
    List<Board> findBoardsByExerciseId(@Param("exerciseId") Long exerciseId);

    List<Board> findBoardsByOrderByMeetStartDateAsc();

    @Modifying(clearAutomatically = true)
    @Query("update Board b set b.title = :title, b.content = :content, b.maxNumber = :maxNumber where b.id = :boardId")
    void updateOneBoard(@Param("title") String title, @Param("content") String content, @Param("maxNumber") int maxNumber, @Param("boardId") Long boardId);

    @Query("select COUNT(b.id) from Board b where b.exercise.id = :exerciseId")
    Long getCountByExercise(@Param("exerciseId") Long exerciseId);
}