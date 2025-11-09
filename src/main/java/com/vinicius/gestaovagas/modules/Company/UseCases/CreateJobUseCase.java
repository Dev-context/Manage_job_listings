package com.vinicius.gestaovagas.modules.Company.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.gestaovagas.expections.JobFoundException;
import com.vinicius.gestaovagas.modules.Company.Repositories.JobRepository;
import com.vinicius.gestaovagas.modules.Company.entities.JobEntity;

@Service
public class CreateJobUseCase {

    @Autowired
    JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {

        this.jobRepository.findByDescription(jobEntity.getDescription()).ifPresent(Job -> {
            throw new JobFoundException();
        });

        return this.jobRepository.save(jobEntity);
    }

}
