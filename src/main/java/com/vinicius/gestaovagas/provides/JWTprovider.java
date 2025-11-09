package com.vinicius.gestaovagas.provides;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JWTprovider {

    @Value("${security.token.secrety}")
    private String secretKey;

    @Value("${security.duration.secrety}")
    private Integer duration;

    public DecodedJWT validateToken(String token) {
        token = token.replace("Bearer", "").trim();
        System.out.println(token);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        try {
            var jwtValidator = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return jwtValidator;

        } catch (JWTVerificationException jwtVerificationException) {
            jwtVerificationException.printStackTrace();
        }
        return null;

    }

}
