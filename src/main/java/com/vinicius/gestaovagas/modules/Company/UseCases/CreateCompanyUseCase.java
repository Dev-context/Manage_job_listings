package com.vinicius.gestaovagas.modules.Company.UseCases;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vinicius.gestaovagas.expections.UserFoundException;
import com.vinicius.gestaovagas.modules.Company.Repositories.CompanyRepository;
import com.vinicius.gestaovagas.modules.Company.entities.CompanyEntity;

@Service
public class CreateCompanyUseCase {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder encoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepository.findByUserNameOrEmail(companyEntity.getName(), companyEntity.getEmail())
                .ifPresent(User -> {
                    throw new UserFoundException();
                });

        var password = encoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);

        return this.companyRepository.save(companyEntity);
    }

    public List<CompanyEntity> findAll() {
        return new ArrayList<CompanyEntity>(this.companyRepository.findAll());
    }

}
