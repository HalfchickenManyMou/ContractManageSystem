package com.example.study.repository;

import com.example.study.model.entity.Department;
import com.example.study.model.entity.Ranks;
import com.example.study.model.entity.User;
import com.example.study.model.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    RankRepository rankRepository;
    @Test
    void create(){
        Department department = departmentRepository.findByIdx(31).orElse(null);
        Team team = teamRepository.findByIdx(36L).orElse(null);
        Ranks rank = rankRepository.findByIdx(10).orElse(null);
        User user = User.builder()
                .userCode("test")
                .name("test")
                .email("test@naver.com")
                .pwd("1234")
                .phoneNumber("010-1234-1234")
                .department(department)
                .team(team)
                .rank(rank)
                .build();

        System.out.println("---------------User Hibernate-----------------");
        userRepository.save(user);
    }

    @Test
    void read(){
        User user = userRepository.findByUserCode("test").orElse(null);
        System.out.println("--------------User Hibernate--------------");
        System.out.println(user);
    }
}