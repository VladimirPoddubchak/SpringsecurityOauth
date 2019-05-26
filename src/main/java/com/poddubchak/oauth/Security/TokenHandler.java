package com.poddubchak.oauth.Security;

import com.google.common.io.BaseEncoding;
import com.poddubchak.oauth.Model.Role;
import com.poddubchak.oauth.Model.User;
import com.poddubchak.oauth.Service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Component
public class TokenHandler {

    @Autowired
    UserService userService;

    private final SecretKey secretKey;

    public TokenHandler() {
        String jwtKey = "jwtkey1234567890";
        byte[] decodedKey = BaseEncoding.base64().decode(jwtKey);
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public Optional<Long> extractUserId(@NonNull String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            return Optional
                    .ofNullable(body.getId())
                    .map(Long::new);

        } catch (RuntimeException e) {
            return Optional.empty();
        }

    }
//    public Optional<List<GrantedAuthority>> extractUserAuthorities(@NonNull String token) {
//        try {
//            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
//            Claims body = claimsJws.getBody();
//            return Optional
//                    .ofNullable(header.   get("ROLES"))
//                    .toString().;
//
//        } catch (RuntimeException e) {
//            return Optional.empty();
//        }
//
//    }

    public String generateAccessToken(@NonNull Long id, @NonNull LocalDateTime expires) {

        return Jwts.builder()
                .setId(id.toString())
                .setPayload (userService.findById(id).map(User::getAuthorities).toString())
                .setExpiration(Date.from(expires.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String generateRefreshToken(@NonNull Long id, @NonNull LocalDateTime expires) {
        return Jwts.builder()
                .setId(id.toString())
                .setExpiration(Date.from(expires.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

}
