package com.example.study.repository;

import com.example.study.model.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByIdx(long idx);
    void deleteByIdx(Long idx);
}
