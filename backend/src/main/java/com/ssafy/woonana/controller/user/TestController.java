package com.ssafy.woonana.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("api/test")
public class TestController {

    // 로그인 기능이 잘 작동하는지 확인하기 위한 api
    @PostMapping("/testcontroller")
    public ResponseEntity<String> tokenTestMethod(@AuthenticationPrincipal Long userId){

        return ResponseEntity.ok().body("가져와졌음, id: "+userId);
    }

    @GetMapping("/urltest")
    public ResponseEntity<String> getMsgForTest(){
        return ResponseEntity.ok().body("woonana 백엔드 호출");
    }
}