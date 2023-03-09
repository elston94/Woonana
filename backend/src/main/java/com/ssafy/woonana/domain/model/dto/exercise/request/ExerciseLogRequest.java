package com.ssafy.woonana.domain.model.dto.exercise.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseLogRequest {

    private Long exerciseId;

    private Long boardId;

    public ExerciseLogRequest(long exerciseId, long boardId) {
        this.exerciseId = exerciseId;
        this.boardId = boardId;
    }
}
