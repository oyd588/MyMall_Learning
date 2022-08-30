package com.oyd.mall.tiny.common.utils;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtils.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        String username = userDetails.getUsername();
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


    public Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败", token);
        }
        return claims;
    }


    /**
     * 生成token的过期时间
     *
     * @return
     */
    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取登录用户名
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        String usernameFromToken = getUsernameFromToken(token);
        return usernameFromToken.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }

    /**
     * 判断token是否过期失效
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token){
        Claims claimsFromToken = getClaimsFromToken(token);
        Date expirationDate = claimsFromToken.getExpiration();
        Date now = new Date();
        return now.after(expirationDate);
    }

    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    public String refreshToken(String token){
        Claims claimsFromToken = getClaimsFromToken(token);
        claimsFromToken.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claimsFromToken);
    }

}
