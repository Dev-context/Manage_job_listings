package com.vinicius.gestaovagas.modules.Company.Controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.gestaovagas.modules.Company.UseCases.CreateJobUseCase;
import com.vinicius.gestaovagas.modules.Company.entities.JobEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase jobUseCase;

    @RequestMapping("/")
    public ResponseEntity<Object> createJob(@Valid @RequestBody JobEntity jobEntity) {
        try {

            var result = this.jobUseCase.execute(jobEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
