package com.example.study.service;


import com.example.study.model.front.AdminMenu;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service

public class UserInfoService {
    public List<AdminMenu> getUserInfo(){
        return Arrays.asList(AdminMenu.builder().title("사용자 정보 수정").url("/pages/userInfo").code("userInfo").build());
    }

}
