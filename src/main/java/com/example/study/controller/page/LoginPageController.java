package com.example.study.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/pages/login")
public class LoginPageController {

    @RequestMapping("")
    public ModelAndView login() {
        return new ModelAndView("/pages/login/loginPage");
    }
}
