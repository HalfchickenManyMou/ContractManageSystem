package com.example.study.config;

import com.example.study.filter.JwtAuthenticationFilter;
import com.example.study.filter.JwtAuthorizationFilter;
import com.example.study.repository.UserRepository;
import com.example.study.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 x

                .and()
                .addFilter(corsFilter) //cors 정책에서 벗어남
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtTokenProvider)) // /login 경로 요청 시 실행
                .addFilter(new JwtAuthorizationFilter(authenticationManager(),jwtTokenProvider, userRepository)) // 권한이 필요한 경로 요청 시 실행
                .authorizeRequests()
                .anyRequest().permitAll() //for test
                
//                //로그인은 모두 허용 인증 필요X
//                .antMatchers("/pages/login").permitAll()
//
//                //그 외에는 관리자와 유저 모두 접근 가능
//                .antMatchers("/page/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//
//                //관리자만 접근할 수 있는 페이지
//                .antMatchers("/pages/user/admin/add", "/pages/contract/contractType", "/pages/department" , "/pages/rank")
//                .access("hasRole('ROLE_ADMIN')")
//
//                .anyRequest().permitAll()

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/pages/login")
                .invalidateHttpSession(true)
                .deleteCookies("Authorization");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }
}
