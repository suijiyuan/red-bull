package xyz.outerringroad.redbull.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.outerringroad.redbull.bean.dto.UserDTO;
import xyz.outerringroad.redbull.exception.BizException;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserDTO userDTO) {
        try {
            return Jwts.builder()
                    .subject(userDTO.getUsername())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + this.expiration))
                    .signWith(Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8)))
                    .compact();
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = this.parseToken(token);
            return this.isTokenExpired(claims);
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(Claims claims) {
        return Objects.isNull(claims) || Objects.isNull(claims.getExpiration())
                || claims.getExpiration().before(new Date());
    }

}
