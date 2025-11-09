package com.vinicius.gestaovagas.modules.Candidate.UserCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vinicius.gestaovagas.modules.Candidate.Repositoty.CandidateRepository;
import com.vinicius.gestaovagas.modules.Candidate.dto.ProfileCandidateResponseDto;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDto execute(UUID idCandiadatUuid) {
        var candidate = this.candidateRepository.findById(idCandiadatUuid)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User not found");
                });

        var candidateDto = ProfileCandidateResponseDto.builder()
                .description(candidate.getDescription())
                .userName(candidate.getUserName())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .id(candidate.getId()).build();

        return candidateDto;

    }

}
