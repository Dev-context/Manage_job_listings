package com.vinicius.gestaovagas.modules.Company.entities;

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
@Entity(name = "Company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "The [userName] field is required")
    private String userName;
    @Email(message = "The [email] field must be valid")
    private String email;
    @NotBlank(message = "The [password] field is required")
    private String password;
    private String webSite;
    @NotBlank(message = "The [name] field is required")
    private String name;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
