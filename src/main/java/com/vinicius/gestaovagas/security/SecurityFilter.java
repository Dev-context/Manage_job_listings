package com.vinicius.gestaovagas.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.vinicius.gestaovagas.provides.JWTprovider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTprovider jwTprovider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (request.getRequestURI().startsWith("/company")) {
            if (header != null) {
                var getSubjectToken = this.jwTprovider.srecretJwtWithSubject(header);

                if (getSubjectToken.isEmpty()) {

                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;

                }

                request.setAttribute("company_id", getSubjectToken);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(getSubjectToken,
                        null,
                        Collections.emptyList());

                SecurityContextHolder.getContext().setAuthentication(auth);

            }

        }

        filterChain.doFilter(request, response);
    }

}
