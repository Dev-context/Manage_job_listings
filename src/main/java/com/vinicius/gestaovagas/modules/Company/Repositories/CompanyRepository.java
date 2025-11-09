package com.vinicius.gestaovagas.modules.Company.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinicius.gestaovagas.modules.Company.entities.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByUserNameOrEmail(String user_name, String email);

    Optional<CompanyEntity> findByUserName(String user_name);

}
