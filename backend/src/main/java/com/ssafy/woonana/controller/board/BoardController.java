package com.ssafy.woonana.controller.board;

import com.ssafy.woonana.domain.model.dto.board.request.BoardRequest;
import com.ssafy.woonana.domain.model.dto.board.request.BoardUpdateRequest;
import com.ssafy.woonana.domain.model.dto.board.response.*;
import com.ssafy.woonana.domain.service.board.BoardService;
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

@Api(value = "게시글 API", tags = {"Board"})
@RestController
@RequestMapping("api/main")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping(consumes = {"multipart/form-data"})
    @ApiOperation(value = "글 하나 등록", notes = "새로운 글을 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 작성 성공"),
            @ApiResponse(code = 400, message = "input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 409, message = "글 작성 불가 (작성자가 동시간대에 다른 참여 신청된 경우)", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity registerBoard(@ModelAttribute BoardRequest boardRequest, @AuthenticationPrincipal Long userId) {
        try {
            System.out.println("userId = " + userId);
            boardService.register(boardRequest, userId);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시 글 하나 상세보기", notes = "글 목록에서 선택한 하나의 글에 대해 상세 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 하나 조회 성공"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "해당 글의 정보 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity<BoardDetailResponse> getOneBoard(@PathVariable("boardId") Long boardId) {

        return ResponseEntity.ok(boardService.getOneBoard(boardId));
    }
    @CrossOrigin("*")
    @GetMapping
    @ApiOperation(value = "신청 가능한 글 전체 목록", notes = "메인 페이지의 글 목록을 무한 스크롤로 보여준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 목록 조회 성공"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "글 정보가 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity<List<BoardListResponse>> getAllBoards() {

        return ResponseEntity.ok(boardService.getAllBoards());
    }

    @GetMapping("/search")
    @ApiOperation(value = "미팅 시간이 빠른 순 글 전체 목록", notes = "메인 페이지의 빠른 시간 순 글 목록을 무한 스크롤로 보여준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 목록 조회 성공"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "글 정보가 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity<List<BoardListResponse>> getBoardsByMeet() {
        return ResponseEntity.ok(boardService.getBoardsByMeet());
    }

    @GetMapping("/search/{exerciseId}")
    @ApiOperation(value = "운동별 등록된 글 리스트 조회", notes = "운동을 선택하면 종료되지 않은(모집중/모집마감) 운동별 모든 글을 보여준다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 목록 조회 성공"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "글 정보가 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity<List<BoardListResponse>> getBoardsByExercise(@PathVariable("exerciseId") Long exerciseId) {
        return ResponseEntity.ok(boardService.getBoardsByExercise(exerciseId));
    }

    @GetMapping("/search/count/{exerciseId}")
    @ApiOperation(value = "운동별 등록된 글 개수 조회", notes = "카테고리의 운동 이름 옆에 표시할 등록된 글 중 신청 가능한 글의 개수를 반환한다.")
    public ResponseEntity<Long> getBoardsCountByExercise(@PathVariable("exerciseId") Long exerciseId) {
        return ResponseEntity.ok(boardService.getBoardsCountByExercise(exerciseId));
    }

    @GetMapping("/mypage")
    @ApiOperation(value = "내가 쓴 글 리스트 조회", notes = "마이페이지에서 내가 쓴 모든 글을 보여준다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 목록 조회 성공"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "글 정보가 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity<List<MyBoardListResponse>> getBoardsByUser(@AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(boardService.getBoardsByUser(userId));
    }

    @PatchMapping("/{boardId}")
    @ApiOperation(value = "글 수정 - 제목, 내용, 최대 인원만 수정 가능", notes = "등록된 글을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 수정 성공"),
            @ApiResponse(code = 400, message = "input 오류", response = ErrorResponse.class),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity updateBoard(@RequestBody BoardUpdateRequest boardUpdateRequest, @PathVariable("boardId") Long boardId, @AuthenticationPrincipal Long userId) {
        try {
            boardService.update(boardUpdateRequest, userId, boardId);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/{boardId}/members")
    @ApiOperation(value = "내가 참여 승인된 글의 참여 멤버 목록", notes = "로그인된 사용자가 참여 승인된 글일 때 멤버 목록을 보여준다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "멤버 조회 성공"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "글 정보가 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity<List<ParticipatedMemberResponse>> getParticipatedMembersOfBoard(@PathVariable("boardId") Long boardId) {

        return ResponseEntity.ok(boardService.getParticipations(boardId));
    }

    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "게시 글 하나 삭제", notes = "작성자가 본인이 작성한 글을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "글 하나 삭제 성공"),
            @ApiResponse(code = 401, message = "토큰 만료 || 토큰 없음 || 토큰 오류 => 권한 인증 오류", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "글 정보가 없음", response = ErrorResponse.class),
            @ApiResponse(code = 500, message = "서버 에러", response = ErrorResponse.class)
    })
    public ResponseEntity deleteBoard(@PathVariable("boardId") Long boardId, @AuthenticationPrincipal Long userId) {
        // TODO: exercise log랑 participations 우선 삭제 필요 (제약조건)

        boardService.deleteBoard(boardId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
