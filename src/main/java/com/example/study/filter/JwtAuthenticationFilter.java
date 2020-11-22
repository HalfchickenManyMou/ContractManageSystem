package com.example.study.filter;

import com.example.study.model.entity.Login;
import com.example.study.model.network.request.LoginRequest;
import com.example.study.util.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//JwtAuthenticationFilter : 인증(로그인)

/*스프링 시큐리티에서 /login 요청하면 UsernamePasswordAuthenticationFilter 동작
loginForm disable 하여 동작을 안함
따라서, 직접 /login 경로일 때 loadUserByUsername 메서드를 불러주는 필터를 생성*/

//로그인 흐름
//1. request 에 담긴 username, password 받는다.
//2. 정상인지 로그인 시도를 한다.
//3. Login(UserDetails)를 세션에 담는다 (스프링 시큐리티의 권한 관리를 사용하기 위해서)
//4. request 를 요청한 사용자에게 JWT 토큰을 response 해준다.


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;


    // 스프링 시큐리티에서 /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper om = new ObjectMapper();
        try {
            LoginRequest user = om.readValue(request.getInputStream(),LoginRequest.class);


            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

            //loadUserByUsername() 함수 실행, token 을 해석해 loadUserByUsername 의 매개변수로 user.getEmail() 이 넘어간다
            //token 에 들어있는 email 과 password 로 스프링 시큐리티가 직접 password 를 확인한다. (loadUserByUsername 내부에서 확인한다)
            //정상이면 authentication 을 리턴한다
            Authentication authentication =
                    authenticationManager.authenticate(authenticationToken);

            //System.out.println("===========loadUserByUsername(out)===========");

            /*user 가 출력되었다는 것은 email 과 password 가 옳바르다는 뜻이다. 로그인 되었다는 뜻
            Login login = (Login) authentication.getPrincipal();
            System.out.println(login.getUser());*/

            //리턴된 authentication 은 스프링 시큐리티 세션에 저장된다
            //저장한 이유는 권한 관리를 스프링 시큐리티가 대신 해주기 때문에 편하려고 하는 것이다.
            //결론 : JWT 를 사용하면서 세션을 만들 이유가 없지만, 권한을 위해 만들었다.
            return authentication;

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    //attemptAuthentication 실행 후 인증이 정상적으로 되면 successfulAuthentication 함수가 실행된다.
    //JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 된다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        Login login = (Login) authResult.getPrincipal();

        //JWT 토큰 생성
        String jsonWebToken = jwtTokenProvider.createToken(login.getUser().getUserCode(), login.getUser().getName());
        //토큰을 헤더에 넣어서 응답

        //웹브라우저 쿠키 생성
        Cookie myCookie = new Cookie("Authorization", jsonWebToken);
        myCookie.setMaxAge(60 * 30);
        myCookie.setPath("/");
        response.addCookie(myCookie);
        //response.addHeader("Authorization", "Bearer " + jsonWebToken);
    }
}
