package com.ssafy.woonana.domain.repository.user;

import com.ssafy.woonana.domain.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 인자로 들어온 카카오 아이디에 해당하는 User 계정을 찾아준다
    Optional<User> findByKakaoId(Long kakaoId);




}
