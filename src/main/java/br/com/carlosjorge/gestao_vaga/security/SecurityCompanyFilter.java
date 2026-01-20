package br.com.carlosjorge.gestao_vaga.security;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.carlosjorge.gestao_vaga.providers.JWTProvider;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityCompanyFilter extends OncePerRequestFilter {

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                   HttpServletResponse response, 
                                   FilterChain filterChain)
            throws ServletException, IOException {

        // Sempre limpar contexto antes de processar
        SecurityContextHolder.clearContext();

        String header = request.getHeader("Authorization");

        // Só aplicamos autenticação para rotas /company (pode expandir se quiser)
        if (request.getRequestURI().startsWith("/company")) {
            if (header != null && header.startsWith("Bearer ")) {
                String rawToken = header.substring(7).trim();

                DecodedJWT token = jwtProvider.validateToken(rawToken);

                if (token == null) {
                    // Token inválido ou expirado
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                // Extrai roles do token
                List<String> roles = token.getClaim("roles").asList(String.class);
                var grants = roles != null
                        ? roles.stream()
                               .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                               .toList()
                        : Collections.emptyList();

                // Injeta o company_id no request para uso em controllers/use cases
                request.setAttribute("company_id", token.getSubject());

                // Cria autenticação do Spring Security
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(token.getSubject(), null);

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}
