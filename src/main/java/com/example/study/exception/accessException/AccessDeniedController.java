package com.example.study.exception.accessException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AccessDeniedController {

    @GetMapping("/accessDenied")
    public ModelAndView accessDenied(){
        return new ModelAndView("/pages/accessDeniedPage");
    }
}
