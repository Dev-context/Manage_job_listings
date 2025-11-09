package com.vinicius.gestaovagas.modules.Candidate.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateResponseDto {

    private UUID id;
    private String name;
    private String email;
    private String userName;
    private String description;

}
