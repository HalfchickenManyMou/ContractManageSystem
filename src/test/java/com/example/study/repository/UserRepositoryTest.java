package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void create(){
        User my = new User();
        my.setUserCode("usercreate");
        my.setName("sunny");
        my.setEmail("njs2417@naver.com");
        my.setPwd("비밀번호");
        my.setDepartmentIdx(9529);
        my.setTeamIdx(8826);
        my.setRankIdx(8862);
        my.setPhoneNumber("010-0000-0000");
        my.setRegisterDate(LocalDateTime.now());
        my.setRegisterUser("hs");
        my.setUpdateDate(LocalDateTime.now());
        my.setUpdateUser("hs");
        User newMy = userRepository.save(my);
    }

    @Test
    public void read(){
        Optional<User> m = userRepository.findById("tttt");
        m.ifPresent(selectUser ->{
            System.out.println("my:" + selectUser);
        });
    }

    @Test
    public void update(){
        Optional<User> userInfo = userRepository.findById("test1");
        userInfo.ifPresent(selectUser -> {
            selectUser.setDepartmentIdx(4321);
            selectUser.setTeamIdx(2345);
            selectUser.setUpdateDate(LocalDateTime.now());
            selectUser.setUpdateUser("hs");
            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete(){
        Optional<User> userInfo = userRepository.findById("tttt");
        userInfo.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });
    }
}
