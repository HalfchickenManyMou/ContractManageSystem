package com.example.study.repository;

import com.example.study.model.entity.Contract;
import com.example.study.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String>, JpaSpecificationExecutor<User>  {

    @EntityGraph("UserWithOtherData")
    Optional<User> findByUserCode(String userCode);

    Optional<User> findByEmail(String email);

    @Transactional
    void deleteByUserCode(String userCode);

}
