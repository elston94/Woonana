package com.ssafy.woonana.domain.model.dto.exercise.response;

import lombok.Data;

import java.util.List;

@Data
public class ExerciseLogByMonthResponse {

    private int excAll;
    private int excMonthCnt;
    private List<ExerciseLogResponse> monthLogs; // 월별 기록

    public ExerciseLogByMonthResponse(int excAll, int excMonthCnt, List<ExerciseLogResponse> monthLogs) {
        this.excAll = excAll;
        this.excMonthCnt = excMonthCnt;
        this.monthLogs = monthLogs;
    }
}
