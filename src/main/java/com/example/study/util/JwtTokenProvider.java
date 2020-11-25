package com.example.study.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private final SecretKey secretKey;
    private final Long tokenValidTime;

    public JwtTokenProvider(){
        String key = "01234567890123456789012345678912";
        secretKey = Keys.hmacShaKeyFor(key.getBytes());
        tokenValidTime = 30 * 60 * 1000L; //30분
    }

    //토큰 생성
    public String createToken(String userCode, String name){
        //토큰 정보 저장
        JwtBuilder builder = Jwts.builder()
                .claim("userCode", userCode)
                .claim("name", name);

        Date now = new Date();
        Date validity = new Date(now.getTime() + tokenValidTime);

        
        return builder
                .signWith(secretKey, SignatureAlgorithm.HS256) //키, 암호화 알고리즘
                .setIssuedAt(now) //토큰 발행 시간 정보
                .setExpiration(validity) //토큰 만료시간
                .compact();
    }

    //만료기간 확인ㅎ
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    //claim
    public Claims getClaims(String token){
        return (Claims) Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parse(token)
                .getBody();
    }
}
