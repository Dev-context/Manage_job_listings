package com.vinicius.gestaovagas.modules.Candidate.Repositoty;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.gestaovagas.modules.Candidate.Entity.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    Optional<CandidateEntity> findByUserNameOrEmail(String user_name, String email);

    Optional<CandidateEntity> findByUserName(String user_name);

}
