package com.vinicius.gestaovagas.modules.Candidate.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "Candidate")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "The [name] field is required")
    private String name;

    @Email(message = "The [email] field must be valid")
    @NotBlank(message = "The name field is required")
    private String email;

    @NotBlank(message = "The [userName] field is required")
    private String userName;

    @NotBlank(message = "The [password] field is required")

    private String password;

    private String description;
    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
