package com.ssafy.woonana.domain.model.dto.board.response;

import com.ssafy.woonana.domain.model.entity.board.Board;
import com.ssafy.woonana.domain.model.entity.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDetailResponse {

    private String userNickname;
    private String userEmail;
    private String title;
    private String content;
    private String place;
    private LocalDateTime createdDate;
    private LocalDateTime meetStartDate;
    private LocalDateTime meetEndDate;
    private int allowedNumber;
    private int maxNumber;
    private int participationOption;
    private Long exerciseId;
    private String status;
    private String imageUrl;

    public BoardDetailResponse(Board board, User user) {
        this.userNickname = user.getUserNickname();
        this.userEmail = user.getUserEmail();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.place = board.getPlace();
        this.createdDate = board.getCreatedDate();
        this.meetStartDate = board.getMeetStartDate();
        this.meetEndDate = board.getMeetEndDate();
        this.allowedNumber = board.getAllowedNumber();
        this.maxNumber = board.getMaxNumber();
        this.participationOption = board.getParticipationOption();
        this.exerciseId = board.getExercise().getId();
        this.status = board.getStatus();
        this.imageUrl = board.getImageUrl();
    }
}
