package com.example.gdkim.config.auth;

import com.example.gdkim.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

        /*
          .csrf().disable().headers().frameOptions().disable()
          h2-console 화면을 사용하기 위해 해당 옵션 disable

          authorizeRequests
          URL별 권한 관리 설정옵션의 시작점

          antMatchers
          권한 관리 대상 지정 옵션
          "/" 등 지정된 URL은 permitAll 전체열람 권한
          "/api/v1/**" api는 USER 권한 가진사람만

          anyRequest
          설정된 값 이외 나머지 URL

          authenticated
          인증된 사용자만 허용 ( 로그인한 사용자 )

          .logout().logoutSuccessUrl("/")
          로그아웃시 "/"로 돌아감

          oauth2Login
          OAuth 로그인 기능 여러 설정의 진입점

          userInfoEndpoint
          OAuth 로그인 성공 후 사용자 정보 가져올때 설정 담당
         */
    }
}
