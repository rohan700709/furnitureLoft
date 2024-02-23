package com.niit.userAuthenticationService.security;

import com.niit.userAuthenticationService.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator {

    @Override
    public Map<String, String> generateToken(User user) {

        String jwtToken = null;
        jwtToken = Jwts.builder().setId(user.getUserEmail()).setSubject(user.getUserRole())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        map.put("message", "User Successfully Logged In");
        return map;
    }
}
