package com.ssafy.woonana.domain.model.dto.user.response;

import com.ssafy.woonana.domain.model.entity.board.Board;
import com.ssafy.woonana.domain.model.entity.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotEvaluatedPerson {

    private Long userId;
    private String userNickname;
    private Long boardId;
    private String boardTitle;
    private LocalDateTime boardDate;
    private Long exerciseId;

    public NotEvaluatedPerson(User user, Board board){
        this.userId=user.getUserId();
        this.userNickname=user.getUserNickname();
        this.boardId=board.getId();
        this.boardTitle=board.getTitle();
        this.boardDate=board.getMeetEndDate();
        this.exerciseId=board.getExercise().getId();
    }
}
