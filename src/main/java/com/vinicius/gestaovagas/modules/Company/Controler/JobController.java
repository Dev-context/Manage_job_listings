package com.vinicius.gestaovagas.modules.Company.Controler;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.gestaovagas.modules.Company.UseCases.CreateJobUseCase;
import com.vinicius.gestaovagas.modules.Company.entities.JobEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase jobUseCase;

    @PostMapping("")
    public ResponseEntity<Object> createJob(@Valid @RequestBody JobEntity jobEntity, HttpServletRequest request) {
        try {
            var company_id = request.getAttribute("company_id");
            jobEntity.setCompanyId(UUID.fromString(company_id.toString()));
            var result = this.jobUseCase.execute(jobEntity);

            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
