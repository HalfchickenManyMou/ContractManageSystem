package com.example.study.repository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    @EntityGraph("UserWithOtherData")
    Optional<User> findByUserCode(String userCode);
}
