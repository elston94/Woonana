package com.ssafy.woonana.controller.exercise;

import com.ssafy.woonana.domain.model.dto.exercise.request.ExerciseLogRequest;
import com.ssafy.woonana.domain.model.dto.exercise.response.ExerciseLog;
import com.ssafy.woonana.domain.model.dto.exercise.response.ExerciseLogDate;
import com.ssafy.woonana.domain.model.dto.exercise.response.ExerciseLogResponse;
import com.ssafy.woonana.domain.model.dto.exercise.response.ExerciseLogByMonthResponse;
import com.ssafy.woonana.domain.service.exercise.ExerciseLogService;
import lombok.RequiredArgsConstructor;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExerciseLogController {

    @Autowired
    private ExerciseLogService exerciseLogService;

    @GetMapping("/myexercise/month/{userId}")
    @ApiOperation(value = "월별 운동 기록 조회", notes="userId 사용자의 총 운동 횟수, 월별 운동 기록을 조회한다.")
    public ResponseEntity<ExerciseLogByMonthResponse> exerciseLogMonth(@PathVariable("userId") Long userId){

        return ResponseEntity.ok(exerciseLogService.findExerciseLogByMonth(userId));
    }

    @GetMapping("/myexercise/record/{userId}")
    public ResponseEntity<List<ExerciseLog>> exerciseLogRecord(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(exerciseLogService.findExerciseLogByUserId(userId));
    }

    @GetMapping("/myexercise/likes/{userId}")
    public ResponseEntity<List<ExerciseLogDate>> exerciseLogLikes(@PathVariable("userId")Long userId){
        return ResponseEntity.ok(exerciseLogService.findExerciseLogByLikes(userId));
    }

    @PostMapping("/{boardId}/{exerciseId}")
    public ResponseEntity registerExerciseLog(@PathVariable("boardId") Long boardId, @PathVariable("exerciseId") Long exerciseId, @AuthenticationPrincipal Long userId) {

        //exerciseLogService.register(boardId, exerciseId, userId);

        return ResponseEntity.ok(exerciseLogService.register(boardId, exerciseId, userId));
    }
}
