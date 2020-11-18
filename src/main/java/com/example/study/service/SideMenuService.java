package com.example.study.service;

import com.example.study.model.front.SideMenu;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SideMenuService {
    public List<SideMenu> getSideMenu(String title) {

        System.out.println("title getAdminMenu : "+title);
        return Arrays.asList(
                SideMenu.builder().title("HOME").mode("header").build(),
                SideMenu.builder().title("대시보드").url("/pages/dashboard").mode("item").build().check(title),
                SideMenu.builder().title("계약서").mode("header").build(),
                SideMenu.builder().title("계약서 수정").url("/pages/contract/contractUpdate").mode("item").build().check(title),
                SideMenu.builder().title("관리자").mode("header").build(),
                SideMenu.builder().title("계약서 타입 관리").url("/pages/contract/contractType").mode("item").build().check(title),
                SideMenu.builder().title("부서/팀 관리").url("/pages/department").mode("item").build().check(title),
                SideMenu.builder().title("직위 관리").url("/pages/rank").mode("item").build().check(title),
<<<<<<< HEAD
                SideMenu.builder().title("사용자추가/복사").url("/pages/userAdd").mode("item").build().check(title)
=======

                SideMenu.builder().title("계약서 권한 관리1").url("/pages/authuser").mode("item").build().check(title),
                SideMenu.builder().title("내 정보 조회 및 수정").url("/pages/myInfo").mode("item").build().check(title),
                SideMenu.builder().title("사용자 정보").url("/pages/userInfo").mode("item").build().check(title)
>>>>>>> 1db4c611f3674d06ba7213925fd729f4fdf0275a
                );
    }
}
