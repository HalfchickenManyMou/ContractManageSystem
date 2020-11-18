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
}
