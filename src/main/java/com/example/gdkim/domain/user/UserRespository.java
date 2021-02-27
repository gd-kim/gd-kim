package com.example.gdkim.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {
    // 소셜 로그인 반환되는 값중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자 인지 판단
    Optional<User> findByEmail(String email);
}