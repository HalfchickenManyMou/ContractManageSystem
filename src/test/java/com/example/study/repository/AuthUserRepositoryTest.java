package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.AuthUser;
import com.example.study.model.enumclass.AuthType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class AuthUserRepositoryTest extends StudyApplicationTests {

    @Autowired
    AuthUserRepository authuserRepository;

    @Test
    public void create(){
        AuthUser authUser = new AuthUser();
        authUser.setIdx(1L);
        authUser.setContract_idx(2L);
        authUser.setDepartment_idx(2020);
        authUser.setTeam_idx(1010);
        authUser.setUseridx("khs");
        authUser.setAuth_type(AuthType.read);
        authUser.setRegisterDate(LocalDateTime.now());
        authUser.setRegisterUser("hs");
        authUser.setUpdateDate(LocalDateTime.now());
        authUser.setUpdateUser("hs");
        AuthUser newAuthUser = authuserRepository.save(authUser);
        System.out.println("new Auth_User : " + newAuthUser);
    }

    @Test
    public void read(){
        Optional<AuthUser> authuser = authuserRepository.findById(1L);
        authuser.ifPresent(selectUser ->{
            System.out.println("auth_user:" + selectUser);
        });
    }

    @Test
    public void update(){
        Optional<AuthUser> userInfo = authuserRepository.findById(1L);
        userInfo.ifPresent(selectUser -> {
            selectUser.setContract_idx(1234L);
            selectUser.setDepartment_idx(4321);
            selectUser.setIdx(22L);
            selectUser.setTeam_idx(2345);
            selectUser.setUpdateDate(LocalDateTime.now());
            selectUser.setUpdateUser("hs");
            authuserRepository.save(selectUser);
        });
    }

    @Test
    public void delete(){
        Optional<AuthUser> userInfo = authuserRepository.findById(1L);
        userInfo.ifPresent(selectUser ->{
            authuserRepository.delete(selectUser);
        });
    }
}
