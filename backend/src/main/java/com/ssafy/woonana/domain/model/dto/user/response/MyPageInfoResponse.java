package com.ssafy.woonana.domain.model.dto.user.response;

import com.ssafy.woonana.domain.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MyPageInfoResponse {

    private String userEmail;
    private String userNickname;
    private String userBirthday;
    private String userSex;
    private int userRatingScore;
    private String userProfileUrl;

    public MyPageInfoResponse(User entity){
        this.userEmail=entity.getUserEmail();
        this.userNickname=entity.getUserNickname();
        this.userBirthday=entity.getUserBirthday();
        this.userSex=entity.getUserSex();
        this.userRatingScore=entity.getUserRatingScore();
        this.userProfileUrl=entity.getUserProfileUrl();
    }

}
