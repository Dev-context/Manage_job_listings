package com.vinicius.gestaovagas.modules.Company.UseCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.vinicius.gestaovagas.modules.Company.Repositories.CompanyRepository;
import com.vinicius.gestaovagas.modules.Company.dto.AuthCompanyDTO;
import com.vinicius.gestaovagas.modules.Company.dto.AuthCompanyResponseDTO;
import com.vinicius.gestaovagas.provides.JWTprovider;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secrety}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTprovider JWTprovider;

    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUserName(authCompanyDTO.getUserName()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Company not found");
        });

        // verificar Password

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        var expires_in = Instant.now().plus(Duration.ofHours(2));
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("nodevagas")
                .withExpiresAt(expires_in)
                .withClaim("roles", Arrays.asList("COMPANY"))
                .withSubject(company.getId().toString())
                .sign(algorithm);

        return AuthCompanyResponseDTO.builder().accessToken(token).expires_in(expires_in.toEpochMilli()).build();
    }

}
