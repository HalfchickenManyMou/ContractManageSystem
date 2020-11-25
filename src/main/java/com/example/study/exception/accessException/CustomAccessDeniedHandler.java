package com.example.study.exception.accessException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        //로그인없이 페이지에 접근했을 때
        request.setAttribute("msg", "로그인이 필요합니다.");
        request.setAttribute("nextPage", "/pages/login");

        request.getRequestDispatcher("/accessDenied").forward(request,response);
    }
}
