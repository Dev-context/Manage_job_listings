package com.vinicius.gestaovagas.modules.Candidate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vinicius.gestaovagas.modules.Candidate.UserCases.AuthCandidateUseCase;
import com.vinicius.gestaovagas.modules.Candidate.dto.AuthCandidateRequestDto;

@Controller
@RequestMapping("/candidate")
public class AuthCandidateController {

    @Autowired
    private AuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDto authCandidateRequestDto) {

        try {

            var token = this.authCandidateUseCase.execute(authCandidateRequestDto);
            return ResponseEntity.ok().body(token);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
