package com.vinicius.gestaovagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vinicius.gestaovagas.provides.JWTCandidateProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilterProfile extends OncePerRequestFilter {

    @Autowired
    private JWTCandidateProvider candidateProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        SecurityContextHolder.getContext().setAuthentication(null);

        String header = request.getHeader("Authorization");
        if (request.getRequestURI().startsWith("/candidate")) {
            if (header != null) {
                var token = this.candidateProvider.validateToken(header);

                if (token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                request.setAttribute("candidate_id", token.getSubject());
                System.out.println("----------token Profile ----------");
                System.out.println(token.getSubject());
                System.out.println("----------token Profile ----------");
                System.out.println(token.getClaims());
                System.out.println("----------token Profile ----------");
            }
        }

        filterChain.doFilter(request, response);
    }

}
