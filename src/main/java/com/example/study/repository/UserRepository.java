package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    @EntityGraph("UserWithOtherData")
    Optional<User> findByUserCode(String userCode);

    Optional<User> findByEmail(String email);

    @Transactional
    void deleteByUserCode(String userCode);
}
