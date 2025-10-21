package com.vinicius.gestaovagas.modules.Company.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.gestaovagas.expections.UserFoundException;
import com.vinicius.gestaovagas.modules.Company.Repositories.CompanyRepository;
import com.vinicius.gestaovagas.modules.Company.entities.CompanyEntity;

@Service
public class CreateCompanyUseCase {

    @Autowired
    CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepository.findByUserNameOrEmail(companyEntity.getName(), companyEntity.getEmail())
                .ifPresent(User -> {
                    throw new UserFoundException();
                });

        return this.companyRepository.save(companyEntity);
    }

}
