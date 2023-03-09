package com.ssafy.woonana.controller.exercise;

import com.ssafy.woonana.domain.model.dto.exercise.request.ExerciseRequest;
import com.ssafy.woonana.domain.service.exercise.ExerciseService;
import com.ssafy.woonana.error.exception.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "운동 등록 API(관리자용)", tags = {"Exercise"})
@RestController
@RequestMapping("admin/exercise")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping
    @ApiOperation(value = "운동 하나 이상 등록", notes = "새로운 운동 종류를 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "운동 등록 성공"),
            @ApiResponse(code = 400, message = "input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity registerExercise(@RequestBody ExerciseRequest exerciseRequest) {
        exerciseService.register(exerciseRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{exerciseId}")
    @ApiOperation(value = "운동 하나 삭제", notes = "등록되어있는 운동을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "운동 삭제 성공"),
            @ApiResponse(code = 400, message = "input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity deleteExercise(@PathVariable("exerciseId") Long exerciseId) {
        exerciseService.delete(exerciseId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
