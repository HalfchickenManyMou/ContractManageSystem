package com.example.study.filter;

import com.example.study.model.entity.Login;
import com.example.study.model.entity.User;
import com.example.study.repository.UserRepository;
import com.example.study.util.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

//JwtAuthorizationFilter : 권한 부여(로그인 중인지 판별)

//권한이나 인증이 필요한 주소는 스프링 시큐리티 필터 중 BasicAuthenticationFilter 필터를 타게 되어있음
// 만약 권한이나 인증이 필요하지 않으면 이 필터를 타지 않는다.
// 따라서, JwtAuthorizationFilter 는 BasicAuthenticationFilter 를 상속받아
// 권한이나 인증이 필요한 주소는 JwtAuthorizationFilter 를 거치게 만든다

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository)
    {
        super(authenticationManager);
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }


    //권한이 필요한 주소요청이 있을 때 해당 필터를 타게 됨. /login 은 이 필터를 거치지 않음.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //브라우저의 쿠키에서 토큰을 받아온다
        Cookie cookie = Arrays.stream(request.getCookies()).filter(r -> r.getName().equals("Authorization")).findFirst().orElse(null);

        //비정상적인 사용자 판별(쿠키가 없는 브라우저)
        if(cookie == null){
            //로그인 페이지로 이동
            chain.doFilter(request,response);
            return;
        }

        try{
            String jsonWebToken = cookie.getValue();
            Claims claims = jwtTokenProvider.getClaims(jsonWebToken);

            //토큰 유효성 검사(존재)
            if(claims.get("userCode").toString() != null){

                User user = userRepository.findByUserCode(claims.get("userCode").toString()).orElse(null);

                //Authentication 객체로 만들어주기 위해 login(UserDetails) 객체로 만들어줌
                Login login = new Login(user);

                Authentication authentication =
                        new UsernamePasswordAuthenticationToken(
                                login,
                                null,
                                login.getAuthorities()
                        );

                //시큐리티 세션에 저장, 권한을 위해
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            chain.doFilter(request,response);

        }
        //토큰 기간 만료시 강제 로그아웃
        catch (ExpiredJwtException exception){
            response.sendRedirect("/logout");
        }
    }
}
