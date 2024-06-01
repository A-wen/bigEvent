package com.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtTest {

    @Test
    public void testOne() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "蔡先生");

        //產生jwt
        String tokem = JWT.create()
                .withClaim("user", claims)//加入附載
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256("key"));
        System.out.println(tokem);
    }

    @Test
    public void testTwo() {
        //驗證用
        String token = "";
        JWTVerifier ier = JWT.require(Algorithm.HMAC256("key")).build();
        DecodedJWT code = ier.verify(token);
        Map<String, Claim> claims = code.getClaims();
        System.out.println(claims);
    }
}
