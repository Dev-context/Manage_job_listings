package com.vinicius.gestaovagas.modules.Company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthCompanyDTO {

    private String password;
    private String userName;
}
