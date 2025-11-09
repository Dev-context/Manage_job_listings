package com.vinicius.gestaovagas.provides;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
public class JWTprovider {

    @Value("${security.token.secrety}")
    private String secretKey;

    @Value("${security.duration.secrety}")
    private Integer duration;

    public String validateToken(String token) {
        token = token.replace("Bearer", "").trim();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        try {
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException jwtVerificationException) {
            jwtVerificationException.printStackTrace();
        }
        return "";

    }

    public String srecretJwtWithSubject(String uuid) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("nodevagas")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(duration)))
                .withSubject(uuid)
                .sign(algorithm);

        return token;
    }
}
