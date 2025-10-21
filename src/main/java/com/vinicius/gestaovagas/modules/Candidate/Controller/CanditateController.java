package com.vinicius.gestaovagas.modules.Candidate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinicius.gestaovagas.modules.Candidate.Entity.CandidateEntity;
import com.vinicius.gestaovagas.modules.Candidate.UserCases.CreateCandidateUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CanditateController {

  @Autowired
  private CreateCandidateUseCase candidateUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidate) {
    try {
      var result = this.candidateUseCase.execute(candidate);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

}
