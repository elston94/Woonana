package com.ssafy.woonana.domain.model.entity.evaluation;

import com.ssafy.woonana.domain.model.entity.board.Board;
import com.ssafy.woonana.domain.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {

    @Id
    @GeneratedValue
    private Long evaluationId;

    @Column
    private int evaluationRatingScore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EVALUATION_USER_ID")
    private User evaluationUser; // 평가한 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="EVALUATION_TARGET_ID")
    private User evaluationTarget; // 평가 받은 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_ID")
    private Board board; // 평가 게시글

}
