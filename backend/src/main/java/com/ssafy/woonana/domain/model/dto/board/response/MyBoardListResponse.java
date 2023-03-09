package com.ssafy.woonana.domain.model.dto.board.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyBoardListResponse {
    private Long boardId;
    private String title;
    private int allowedNumber;
    private int maxNumber;
    private String status;
}
