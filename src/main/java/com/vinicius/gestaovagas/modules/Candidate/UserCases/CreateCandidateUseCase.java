package com.vinicius.gestaovagas.modules.Candidate.UserCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinicius.gestaovagas.expections.UserFoundException;
import com.vinicius.gestaovagas.modules.Candidate.Entity.CandidateEntity;
import com.vinicius.gestaovagas.modules.Candidate.Repositoty.CandidateRepository;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUserNameOrEmail(candidateEntity.getUserName(), candidateEntity.getEmail())
                .ifPresent((isPresent) -> {
                    throw new UserFoundException();
                });
        ;
        return this.candidateRepository.save(candidateEntity);
    }

}
