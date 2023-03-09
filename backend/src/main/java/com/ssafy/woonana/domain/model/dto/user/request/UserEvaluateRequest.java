package com.ssafy.woonana.domain.model.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEvaluateRequest {

    private Long userId;
    private int rating;
    private Long boardId;

}
