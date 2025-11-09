package com.vinicius.gestaovagas.modules.Company.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.gestaovagas.modules.Company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    Optional<JobEntity> findByDescription(String level);

}
