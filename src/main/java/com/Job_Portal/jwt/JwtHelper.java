package com.Job_Portal.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtHelper {

    @Value("${jwt.token.validity}")
    private long JWT_VALIDITY;

    @Value("${jwt.secret}")
    private String SECRET_KEY;


    public String generateToken(UserDetails userDetails) {

        List<String> role = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
            role.add(grantedAuthority.getAuthority());

        }

        CustomUserDetails customUserDetails =(CustomUserDetails)userDetails;

        Map<String, Object> clams = new HashMap<>();
        clams.put("id", customUserDetails.getId());
        clams.put("name", customUserDetails.getName());
        clams.put("accountType",customUserDetails.getAccountType());
        clams.put("profileId",customUserDetails.getProfileId());
        clams.put("roles", customUserDetails.getAuthorities());

        String token = Jwts.builder()
                .claims(clams)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis())) //issueDate
                .expiration(new Date(System.currentTimeMillis() + JWT_VALIDITY * 1000)) //expiredTime
                .signWith(getKey(), Jwts.SIG.HS512)  //secret key
                .compact();


        return token;

    }

    private SecretKey getKey() {
        byte[] bytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(bytes);
    }


    //================================================================
    private Jws<Claims> getAllDetailsFromToken(String token) {
        JwtParserBuilder parser = Jwts.parser();

        return parser.verifyWith(getKey()).build().parseSignedClaims(token);

    }

    ;

    public String getUsernameFromToken(String token) {

        return getAllDetailsFromToken(token).getPayload().getSubject();

    }

    public boolean isExpired(String token) {
        Date expired = getAllDetailsFromToken(token).getPayload().getExpiration();
        return expired.before(new Date()); //true => account is expired;
        //false => account is not expired;

    }


}
