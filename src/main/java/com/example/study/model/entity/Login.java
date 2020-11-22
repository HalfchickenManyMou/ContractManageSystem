package com.example.study.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


/*시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
로그인 진행이 완료되면 시큐리티에서 세션을 만들어준다
이때 세션에 저장되는 객체의 타입은 Authentication 형태이어야 한다
Authentication 안에는 유저 정보가 있어야 하는데
이때, 유저 정보 객체의 타입은 UserDetails 타입 객체이여야 한다.*/

@Data
public class Login implements UserDetails {

    private final User user;

    public Login(User user){
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPwd();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
