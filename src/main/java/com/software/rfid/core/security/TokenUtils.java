package com.software.rfid.core.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/** Daniel Nacher 2023-04-23 */
public class TokenUtils {

  private TokenUtils() {}

  private static final String ACCESS_TOKEN_SECRET = "TjWnZr4t7w!z%C*F-JaNdRgUkXp2s5v8";
  private static final Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L; // 30 dias

  public static String createToken(String email) {
    long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
    Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

    return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthetication(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();

            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException ex){
            return null;
        }
    }
}
