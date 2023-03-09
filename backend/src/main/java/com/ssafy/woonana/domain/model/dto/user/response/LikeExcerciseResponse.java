package com.ssafy.woonana.domain.model.dto.user.response;

import lombok.*;

@Data
@NoArgsConstructor
public class LikeExcerciseResponse {

    private Long excId; // 운동 아이디
    private int excCount; // 사용자 운동한 횟수

    public LikeExcerciseResponse(Long excId, int excCount){
        this.excId=excId;
        this.excCount=excCount;
    }
}
