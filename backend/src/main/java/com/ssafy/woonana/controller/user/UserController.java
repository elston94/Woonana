package com.ssafy.woonana.controller.user;

import com.ssafy.woonana.domain.model.dto.exercise.response.ExerciseLogCountResponse;
import com.ssafy.woonana.domain.model.dto.user.request.UserEvaluateRequest;
import com.ssafy.woonana.domain.model.dto.user.response.*;
import com.ssafy.woonana.domain.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("api/accounts")
public class UserController {

    @Autowired
    UserService userService;

    // 유저가 회원이면 로그인, 유저가 회원이 아니라면 회원가입을 시키고 나서 로그인한다.
    @PostMapping("/signup")
    @ApiOperation(value = "회원 기능", notes = "유저가 회원이면 로그인, 유저가 회원이 아니라면 회원가입 시킨 후 로그인.")
    public ResponseEntity<HashMap<String, String>> userLogin(@RequestParam String code) throws Exception {

        HashMap<String, String> result = new HashMap<>(); 
        String token = userService.userAccess(code); // 회원 가입 or 로그인 후 토큰 생성 
        result.put("token", token);
        
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/mypage")
    @ApiOperation(value = "회원 정보 삭제", notes = "카카오 권한 삭제하고 회원 정보도 삭제한다.")
    public ResponseEntity<HashMap<String, String>> userDelete(@AuthenticationPrincipal Long userId) throws Exception {

        if(!userService.userDelete(userId)){
            throw new Exception("회원 정보 삭제에 실패했습니다.");
        }

        HashMap<String, String> result = new HashMap<>();
        result.put("delete", userId+"번 회원이 탈퇴했습니다.");

        return ResponseEntity.ok().body(result);

    }

    @GetMapping("/mypage")
    @ApiOperation(value="회원 정보", notes="지금 로그인한 회원 정보를 조회한다.")
    public ResponseEntity<MyPageInfoResponse> getMyPageInformation(@AuthenticationPrincipal Long userId) {

        MyPageInfoResponse result = userService.selectUserInfo(userId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/likes")
    @ApiOperation(value="내 운동 선호도 조회", notes = "로그인한 사용자의 운동 선호도를 조회한다.")
    public ResponseEntity<List<ExerciseLogCountResponse>> getExcerciseLike(@AuthenticationPrincipal Long userId){

        List<ExerciseLogCountResponse> result = userService.getLikeExercise(userId);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/evalue")
    @ApiOperation(value="평가하기", notes="사용자의 점수를 평가한다.")
    public ResponseEntity<String> evaluate(@RequestBody UserEvaluateRequest evaluation, @AuthenticationPrincipal Long userId){

        userService.evaluate(userId, evaluation.getUserId(), evaluation.getRating(), evaluation.getBoardId());
        return ResponseEntity.ok().body("평가 완료");
    }

    @GetMapping("/evalue")
    @ApiOperation(value="평가내역", notes="내가 평가한 모든 사용자들의 평가 내역을 보여준다.")
    public ResponseEntity<List<UserEvaluateResponse>> getEvaluateList(@AuthenticationPrincipal Long userId){

        List<UserEvaluateResponse> result = userService.getEvaluationList(userId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/participate")
    @ApiOperation(value="참여내역", notes="사용자가 참여한 게시글을 최신 순으로 보여준다.")
    public ResponseEntity<List<UserParticipateResponse>> getParticipateList(@AuthenticationPrincipal Long userId){

        List<UserParticipateResponse> result = userService.getParticipationList(userId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/info")
    @ApiOperation(value="채팅용 - 로그인한 회원 정보", notes="로그인한 회원의 닉네임, 회원 아이디, 프로필 사진을 리턴한다.")
    public ResponseEntity<ChatingUserInfoResponse> getChatingUserInfo(@AuthenticationPrincipal Long userId){

        ChatingUserInfoResponse result = userService.getUserInfo(userId);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/evalue/person")
    @ApiOperation(value="평가할 사람", notes="아직 평가하지 않은 사람들의 목록을 보여준다.")
    public ResponseEntity<List<NotEvaluatedPerson>> notEvaluatedPeople(@AuthenticationPrincipal Long userId) {

        List<NotEvaluatedPerson> result=userService.notEvaluatedPeople(userId);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/evalue/me")
    @ApiOperation(value="내 평점 평균", notes="내가 받은 평점 평균")
    public ResponseEntity<HashMap<String, Integer>> myEvalueAvg(@AuthenticationPrincipal Long userId){

        HashMap<String, Integer> result = userService.avgUserRatingScore(userId);

        return ResponseEntity.ok().body(result);
    }
}
