package com.vinicius.gestaovagas.modules.Candidate.UserCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vinicius.gestaovagas.modules.Candidate.Repositoty.CandidateRepository;
import com.vinicius.gestaovagas.modules.Candidate.dto.AuthCandidateRequestDto;
import com.vinicius.gestaovagas.modules.Candidate.dto.AuthCandidateResponseDTO;

import jakarta.security.auth.message.AuthException;

@Service
public class AuthCandidateUseCase {

    @Value("{$security.token.candidate}")
    private String candidateSecrety;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder encoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDto authCandidateRequestDto) throws AuthException {
        var candidate = this.candidateRepository.findByUserName(authCandidateRequestDto.userName())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User Name or Password incorrect");
                });

        var passwordMatches = encoder.matches(authCandidateRequestDto.password(), candidate.getPassword());
        if (!passwordMatches) {
            throw new AuthException();
        }

        Algorithm algorithm = Algorithm.HMAC256(candidateSecrety);
        var expiresIn = Instant.now().plus(Duration.ofHours(2));
        var token = JWT.create()
                .withIssuer("nodevagas")
                .withSubject(candidate.getId().toString())
                .withClaim("roles", Arrays.asList("candidate"))
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return AuthCandidateResponseDTO.builder()
                .accessToken(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

    }
}
