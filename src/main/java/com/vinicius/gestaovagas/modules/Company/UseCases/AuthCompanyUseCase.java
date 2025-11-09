package com.vinicius.gestaovagas.modules.Company.UseCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinicius.gestaovagas.modules.Company.Repositories.CompanyRepository;
import com.vinicius.gestaovagas.modules.Company.dto.AuthCompanyDTO;
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

    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUserName(authCompanyDTO.getUserName()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Company not found");
        });

        // verificar Password

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException();
        }

        return JWTprovider.srecretJwtWithSubject(company.getId().toString());

    }

}
