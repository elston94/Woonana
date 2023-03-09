package com.ssafy.woonana.domain.model.dto.user.response;

import com.ssafy.woonana.domain.model.entity.evaluation.Evaluation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvaluateResponse {

    private Long evaluateId; // 평가 아이디
    private Long userId; // 평가 받은 사람
    private String userNickname; // 평가 받은 사람 닉네임
    private int rating; // 평가 점수

    private LocalDateTime boardDate; // 게시글의 모임 날짜

    private String boardTitle; // 게시글 제목

    public UserEvaluateResponse(Evaluation eval, String userNickname){
        this.evaluateId=eval.getEvaluationId();
        this.userId=eval.getEvaluationTarget().getUserId(); // 평가 받은 사람
        this.userNickname=userNickname;
        this.rating=eval.getEvaluationRatingScore();
        this.boardDate=eval.getBoard().getMeetEndDate();
        this.boardTitle=eval.getBoard().getTitle();
    }

}
