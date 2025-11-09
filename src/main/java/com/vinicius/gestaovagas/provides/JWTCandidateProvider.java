
package com.vinicius.gestaovagas.provides;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTCandidateProvider {

    @Value("{$security.token.candidate}")
    private String candidateSecrety;

    public DecodedJWT validateToken(String token) {

        token = token.replace("Bearer", "").trim();
        Algorithm algorithm = Algorithm.HMAC256(candidateSecrety);
        try {
            System.out.println(token);
            var tokenJwt = JWT.require(algorithm).build().verify(token);

            return tokenJwt;
        } catch (JWTVerificationException jwtVerificationException) {

            jwtVerificationException.printStackTrace();
            return null;
        }

    }

}