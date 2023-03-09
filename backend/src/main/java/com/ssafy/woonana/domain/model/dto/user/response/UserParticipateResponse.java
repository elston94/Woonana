package com.ssafy.woonana.domain.model.dto.user.response;

import com.ssafy.woonana.domain.model.entity.board.Board;
import com.ssafy.woonana.domain.model.entity.exercise.Exercise;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserParticipateResponse { // 참여 내역 조회

    private Long boardId;
    private String place;
    private String boardMeetStartDate;
    private Long exerciseId;
    private String exerciseName;

    @Builder
    public UserParticipateResponse(Board b){
        this.boardId = b.getId();
        this.place = b.getPlace();
        this.boardMeetStartDate = b.getMeetStartDate().toString().split("T")[0];
        this.exerciseId = b.getExercise().getId();
        this.exerciseName = b.getExercise().getName();
    }
}
