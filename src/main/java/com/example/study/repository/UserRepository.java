package com.example.study.repository;

import com.example.study.model.entity.User;
<<<<<<< HEAD
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
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);

>>>>>>> 1db4c611f3674d06ba7213925fd729f4fdf0275a
}
