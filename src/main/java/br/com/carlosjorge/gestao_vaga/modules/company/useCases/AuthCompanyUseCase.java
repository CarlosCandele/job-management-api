package br.com.carlosjorge.gestao_vaga.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.carlosjorge.gestao_vaga.modules.company.dto.AuthCompanyDTO;
import br.com.carlosjorge.gestao_vaga.modules.company.dto.AuthCompanyResponseDTO;
import br.com.carlosjorge.gestao_vaga.modules.company.repositoreis.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername())
            .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));

        boolean passwordMatches = passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new BadCredentialsException("Username/password incorrect");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        String token = JWT.create()
            .withIssuer("vagas back-end")
            .withSubject(company.getId().toString())
            .withExpiresAt(expiresIn)
            .withClaim("roles", Arrays.asList("COMPANY"))
            .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
        .acess_token(token)
        .expires_in(expiresIn.toEpochMilli())
        .build();

        return authCompanyResponseDTO;
    }
}
