package com.example.study.exception.accessException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        //로그인 이후 권한 없는 페이지에 들어갔을 때
        request.setAttribute("msg", "접근권한이 없는 사용자입니다.");
        request.setAttribute("nextPage", "/pages/dashboard");

        request.getRequestDispatcher("/accessDenied").forward(request,response);
    }
}
