package br.com.carlosjorge.gestao_vaga.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.carlosjorge.gestao_vaga.providers.JWTCandidateProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

    @Autowired
    private JWTCandidateProvider jwtCandidateProvider;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();

        return uri.startsWith("/actuator")
            || uri.startsWith("/swagger")
            || uri.startsWith("/v3/api-docs")
            || !uri.startsWith("/candidate");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        var token = jwtCandidateProvider.validateToken(header);

        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        var roles = token.getClaim("roles").asList(String.class);

        var authorities = roles.stream()
            .map(r -> new SimpleGrantedAuthority("ROLE_" + r.toUpperCase()))
            .toList();

        var auth = new UsernamePasswordAuthenticationToken(
            token.getSubject(), null, authorities
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }
}

