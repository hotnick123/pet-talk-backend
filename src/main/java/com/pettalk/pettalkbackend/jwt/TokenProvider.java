package com.pettalk.pettalkbackend.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class TokenProvider {
    String secretKey = "hotnick123hotnick123hotnick123hotnick123";

    public String createToken(String id, String issuer, String subject) {

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 표준 클레임 셋팅
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
//                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(getSignKey());

        // 토큰 만료 시간 셋팅
        long expMillis = nowMillis + 100000;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        // 토큰 생성
        return builder.compact();
    }

    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
}
