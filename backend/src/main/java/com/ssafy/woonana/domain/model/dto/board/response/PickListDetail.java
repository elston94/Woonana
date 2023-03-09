package com.ssafy.woonana.domain.model.dto.board.response;

import lombok.Data;

@Data
public class PickListDetail {
    private Long participationId;
    private Long userId;
    private String userNickname;
    private String profileUrl;
    private Boolean participationAllowed;
    private int userRatingScore;
}
