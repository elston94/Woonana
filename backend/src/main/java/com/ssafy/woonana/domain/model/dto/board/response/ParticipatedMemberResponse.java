package com.ssafy.woonana.domain.model.dto.board.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParticipatedMemberResponse {
    private Long userId;
    private String userNickname;
    private String userProfileUrl;
    private String userSex;
    private int userRatingScore;
}
