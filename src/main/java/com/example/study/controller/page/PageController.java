package com.example.study.controller.page;

import com.example.study.service.SideMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class PageController {

    @Autowired
    SideMenuService sideMenuService;

    @RequestMapping(path = {"/dashboard"})
    public ModelAndView dashboardPage() {
        return new ModelAndView("/pages/dashboardPage")
                .addObject("menuList", sideMenuService.getSideMenu("대시보드"))
                .addObject("code", "user")
                ;
    }

    @RequestMapping(path = {"/contract/contractType"})
    public ModelAndView contractTypePage(){
        return new ModelAndView("/pages/contractTypePage")
                .addObject("menuList", sideMenuService.getSideMenu("계약서 타입 관리"))
                .addObject("code", "user")
                ;
    }

    @RequestMapping(path = {"/department"})
    public ModelAndView departmentPage() {
        return new ModelAndView("/pages/departmentPage")
                .addObject("menuList", sideMenuService.getSideMenu("부서/팀 관리"))
                .addObject("code", "user")
                ;
    }

    @RequestMapping(path = {"/rank"})
    public ModelAndView rankPage() {
        return new ModelAndView("/pages/rankPage")
                .addObject("menuList", sideMenuService.getSideMenu("직위 관리"))
                .addObject("code", "user")
                ;
    }

<<<<<<< HEAD
    @RequestMapping(path ={"/userAdd"})
    public ModelAndView userAdd(){
        return new ModelAndView("/pages/userAddCopyPage")
                .addObject("menuList",sideMenuService.getSideMenu("사용자추가/복사"))
                .addObject("code","user")
                ;
    }
    @RequestMapping(path={"/inquire"})
    public ModelAndView inquire(){
        return new ModelAndView("/pages/inquirePage");
    }

    @RequestMapping(path={"/contract/contractUpdate"})
    public ModelAndView contractUpdate(){
        return new ModelAndView("/pages/contractUpdatePage");
    }
=======

    @RequestMapping(path = {"/authuser"})
    public ModelAndView authuserPage() {
        return new ModelAndView("/pages/authuserPage")
                .addObject("menuList", sideMenuService.getSideMenu("계약서 권한 관리1"))
                .addObject("code", "user")
                ;
    }
    @RequestMapping(path = {"/authuser/authuserdetail"})
    public ModelAndView authuserPageDetail() {
        return new ModelAndView("/pages/authuserPageDetail")
                .addObject("menuList", sideMenuService.getSideMenu("계약서 권한 관리2"))
                .addObject("code", "user")
                ;
    }

    @RequestMapping(path = {"/myInfo"})
    public ModelAndView myInfoPage() {
        return new ModelAndView("/pages/myInfo")
                .addObject("menuList", sideMenuService.getSideMenu("내 정보 조회 및 수정"))
                .addObject("code", "user")
                ;
    }

    @RequestMapping(path = {"/userInfo"})
    public ModelAndView userInfoPage() {
        return new ModelAndView("/pages/userInfo")
                .addObject("menuList", sideMenuService.getSideMenu("사용자 정보"))
                .addObject("code", "user")
                ;
    }






>>>>>>> 1db4c611f3674d06ba7213925fd729f4fdf0275a
}
