package com.ssafy.woonana.config;

import com.ssafy.woonana.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/static/css/**, /static/js/**, *.ico");
        // swagger
        web.ignoring().antMatchers(
                "/v2/api-docs", "/configuration/ui",
                "/swagger-resources", "/configuration/security",
                "/swagger-ui.html", "/webjars/**","/swagger/**", "/api/test/**",
                "/api/accounts/signup/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // HttpSecurity: 시큐리티 설정을 위한 오브젝트

        http.cors() //WebMvcConfig에서 이미 한번 설정했으므로 기본 cors 설정
                .and()
                .csrf() // csrf는 현재 사용하지 않으므로 disable
                .disable()
                .httpBasic() // token을 사용하므로 basic 인증 siable
                .disable()
                .sessionManagement() // session 기반이 아님
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // /account/** 경로는 인증 안 해도 됨
                .antMatchers("/","/api/accounts/signup/**",
                        "/swagger-ui/**",
                        "/swagger-resources/**","/api/accounts/mypage/**",
                        "/api/test/**").permitAll()
                .anyRequest()
                .authenticated();

        // filter 등록
        // 매 요청마다 CorsFilter를 실행한 다음 jwtAuthenticationFilter를 실행한다
        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );

    }
}
