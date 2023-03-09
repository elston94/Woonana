package com.ssafy.woonana.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter { // 요청 한번당 한번만 실행되는 필터

    @Autowired
    private TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // 요청에서 토큰 가져오기
            String token = parseBearerToken(request);
            log.info("jwt 토큰 인증 시작");

            if(token!=null && !token.equalsIgnoreCase("null")) { // 토큰이 null이 아닌 경우
                Long userId=tokenProvider.validateAndGetUserId(token); // userId 가져오기
                log.info("인증된 userId: "+userId);

                // 인증 완료. SecurityContextHolder에 등록해야 인증된 사용자라고 생각한다.
                AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userId, // 인증된 사용자 아이디
                        null,
                        AuthorityUtils.NO_AUTHORITIES
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext= SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication); // 인증 정보 넣음
                SecurityContextHolder.setContext(securityContext); // 인증 정보 등록->백엔드 코드 다른 곳에서 불러올 수 있음
            }
            else {
                log.info("토큰 값이 null.");
            }
        }
        catch (Exception ex) {
            logger.error("인증 오류가 발생했습니다.", ex);
        }

        filterChain.doFilter(request, response);

    }

    private String parseBearerToken(HttpServletRequest request){

        // http 요청의 헤더를 파싱해 Bearer 토큰을 리턴한다.
        String bearerToken=request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            System.out.println(bearerToken);
            return bearerToken.substring(7); // "Bearer " 일곱글자 빼고 나머지 리턴
        }

        return null;
    }
}

