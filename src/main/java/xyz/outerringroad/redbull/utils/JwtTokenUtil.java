package xyz.outerringroad.redbull.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.outerringroad.redbull.bean.dto.UserDTO;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserDTO userDTO) {
        return Jwts.builder()
                .subject(userDTO.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + this.expiration))
                .signWith(Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

}
