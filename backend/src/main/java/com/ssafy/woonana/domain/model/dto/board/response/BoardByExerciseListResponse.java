package com.ssafy.woonana.domain.model.dto.board.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardByExerciseListResponse {

    private Long exerciseId;
    private Long boardId;
    private String place;
}
