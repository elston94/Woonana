package com.ssafy.woonana.controller.board;

import com.ssafy.woonana.domain.model.dto.board.response.PickListResponse;
import com.ssafy.woonana.domain.service.board.ParticipationService;
import com.ssafy.woonana.error.exception.ErrorResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(value = "참가 API", tags = {"Participation"})
@RestController
@RequestMapping("api/participate")
@RequiredArgsConstructor
public class ParticipationController {

    private final ParticipationService participationService;

    @PostMapping("/{boardId}")
    @ApiOperation(value = "게시글에 참여 신청", notes = "다른 사용자가 해당 게시글의 운동에 참여 신청을 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "참여 신청 성공"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "참여 신청 불가 (신청자가 동시간대에 다른 참여 신청된 경우 || 모집 마감)", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러(참여 신청 불가)", response = ErrorResponse.class)
    })
    public ResponseEntity registerParticipation(@PathVariable("boardId") Long boardId, @AuthenticationPrincipal Long userId) {

        // 해당 board의 인원이 다 찼으면 종료
        participationService.isStatusClose(boardId);
        // 해당 user가 이미 board에 참여 신청 되어있으면 종료
        participationService.isUserRegistered(boardId, userId);

        return ResponseEntity.ok(participationService.register(boardId, userId));
    }

    @GetMapping("/pick/{boardId}")
    @ApiOperation(value = "글 작성자가 신청자 목록 볼 때 사용", notes = "해당 모임에 참가 신청한 사용자 목록")
    @ApiResponses({
            @ApiResponse(code = 200, message = "참여 목록 조회 성공"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "해당 글의 정보 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity<PickListResponse> getAppliedList(@PathVariable("boardId") Long boardId) {

        return ResponseEntity.ok(participationService.getAppliedList(boardId));
    }

    @PatchMapping("/pick/approve/{participationId}")
    @ApiOperation(value = "글 작성자가 신청자 참여 승인할 때 사용", notes = "신청자 목록에서 해당 참여 정보에 대한 승인 여부를 허용한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "참여 승인 성공"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "해당 글의 정보 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity approveParticipation(@PathVariable("participationId") Long participationId) {

        participationService.approve(participationId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/pick/refuse/{participationId}")
    @ApiOperation(value = "글 작성자가 신청자 참여 거절할 때 사용", notes = "신청자 목록에서 해당 참여자의 참여를 거절 후 신청자 목록에서 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "참여 거절 성공(신청 목록에서 삭제)"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "해당 참여 관련 정보 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity refuseParticipation(@PathVariable("participationId") Long participationId) {
        participationService.refuse(participationId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{participationId}")
    @ApiOperation(value = "참여 신청자가 참여 취소할 때 사용", notes = "신청자 목록에서 참여 신청 내역을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "참여 취소 성공(신청 목록에서 삭제)"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "해당 참여 관련 정보 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity cancelParticipation(@PathVariable("participationId") Long participationId) {
        participationService.cancel(participationId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/board")
    @ApiResponses({
            @ApiResponse(code = 200, message = "참여 취소 성공(신청 목록에서 삭제)"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "해당 참여 관련 정보 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity<List<Map<String, Long>>> participateBoardList(@AuthenticationPrincipal Long userId){

        List<Map<String, Long>> result = participationService.participateBoardList(userId);
        return ResponseEntity.ok().body(result);
    }

}
