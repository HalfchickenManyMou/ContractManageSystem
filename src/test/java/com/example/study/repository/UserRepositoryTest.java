package com.example.study.repository;

import com.example.study.model.entity.Department;
import com.example.study.model.entity.Ranks;
import com.example.study.model.entity.User;
import com.example.study.model.entity.Team;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;

import java.util.Optional;

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

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void create(){

        Department department = departmentRepository.findByIdx(31).orElse(null);
        Team team = teamRepository.findByIdx(36L).orElse(null);
        Ranks rank = rankRepository.findByIdx(10).orElse(null);
        User user = User.builder()
                .userCode("test1")
                .name("test2")
                .email("test@naver.com")
                .pwd(passwordEncoder.encode("1234"))
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
        User user = userRepository.findByUserCode("test1").orElse(null);
        System.out.println("--------------User Hibernate--------------");
        System.out.println(user);
    }

    @Test
    void update(){
        User user = userRepository.findByUserCode("test2").orElse(null);
        boolean chk = passwordEncoder.matches("1234", user.getPwd());
        if(chk){
            user.setName("test(변경)")
                .setPhoneNumber("010-0000-0000");

            userRepository.save(user);
        }
    }

    @Test
    void updatePwd(){
        User user = userRepository.findByUserCode("test2").orElse(null);
        boolean chk = passwordEncoder.matches("1234", user.getPwd());
        if(chk){
            user.setPwd(passwordEncoder.encode("12345"));
            userRepository.save(user);

            User updated = userRepository.findByUserCode("test2").orElse(null);
            assertTrue(passwordEncoder.matches("12345", updated.getPwd()));
        }
    }

}