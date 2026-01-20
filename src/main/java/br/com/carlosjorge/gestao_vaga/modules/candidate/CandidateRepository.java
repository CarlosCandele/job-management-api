package br.com.carlosjorge.gestao_vaga.modules.candidate;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository  extends JpaRepository<CandidateEntitty, UUID>{

    Optional<CandidateEntitty> findByUsernameOrEmail(String username, String email);
    Optional<CandidateEntitty> findByUsername(String username);
}
