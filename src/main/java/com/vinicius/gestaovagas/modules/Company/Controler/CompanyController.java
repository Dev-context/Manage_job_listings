package com.vinicius.gestaovagas.modules.Company.Controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vinicius.gestaovagas.modules.Company.UseCases.CreateCompanyUseCase;
import com.vinicius.gestaovagas.modules.Company.entities.CompanyEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase companyUseCase;

    @RequestMapping("")
    public ResponseEntity<Object> createCompany(@Valid @RequestBody CompanyEntity companyEntity) {

        try {

            var result = this.companyUseCase.execute(companyEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("")
    public List<CompanyEntity> getAll() {
        return companyUseCase.findAll();

    }

}
