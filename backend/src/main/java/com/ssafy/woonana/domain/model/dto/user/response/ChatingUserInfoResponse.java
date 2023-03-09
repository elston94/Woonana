package com.ssafy.woonana.domain.model.dto.user.response;

import com.ssafy.woonana.domain.model.entity.user.User;
import lombok.Data;

@Data
public class ChatingUserInfoResponse {

    private Long userId;
    private String nickname;
    private String profileUrl;

    public ChatingUserInfoResponse(User user) {
        this.userId=user.getUserId();
        this.nickname=user.getUserNickname();
        this.profileUrl=user.getUserProfileUrl();
    }
}
