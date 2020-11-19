package com.example.study.controller.page;

import com.example.study.model.network.response.ContractTypeResponse;
import com.example.study.service.ContractTypeApiLogicService;
import com.example.study.service.SideMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


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

    @RequestMapping(path = {"/user/myInfo"})
    public ModelAndView userInfoPage() {
        return new ModelAndView("/pages/userInfoPage")
                .addObject("menuList", sideMenuService.getSideMenu("내 정보 조회"))
                .addObject("code", "user")
                ;
    }

    @RequestMapping(path = {"/user/myInfo/edit"})
    public ModelAndView userInfoEditPage() {
        return new ModelAndView("/pages/userInfoEditPage")
                .addObject("menuList", sideMenuService.getSideMenu("내 정보 수정"))
                .addObject("code", "user")
                ;
    }

    @RequestMapping(path = {"/user/admin/add"})
    public ModelAndView userAddCopyPage() {
        return new ModelAndView("/pages/userAddCopyPage")
                .addObject("menuList", sideMenuService.getSideMenu("사용자 추가"))
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

    @RequestMapping(path = {"/contract"})
    public ModelAndView contractPage() {
        return new ModelAndView("/pages/contractPage")
                .addObject("menuList", sideMenuService.getSideMenu("계약관리"))
                .addObject("code", "user")
                ;
    }

    @RequestMapping(path = {"/auth"})
    public ModelAndView authuserPage() {
        return new ModelAndView("/pages/authuserPage")
                .addObject("menuList", sideMenuService.getSideMenu("계약서 권한 관리1"))
                .addObject("code", "user")
                ;
    }
    @RequestMapping(path = {"/authuser"})
    public ModelAndView authuserPageDetail() {
        return new ModelAndView("/pages/authuserPageDetail")
                .addObject("menuList", sideMenuService.getSideMenu("계약서 권한 관리2"))
                .addObject("code", "user")
                ;
    }


    @RequestMapping(path = {"/authInquire"})
    public ModelAndView authInquirePage() {
        return new ModelAndView("/pages/authInquire")
                .addObject("menuList", sideMenuService.getSideMenu("사용자 정보"))
                .addObject("code", "user")
                ;
    }

}
