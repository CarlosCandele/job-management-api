package br.com.carlosjorge.gestao_vaga.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.carlosjorge.gestao_vaga.exceptions.UserFoundException;
import br.com.carlosjorge.gestao_vaga.modules.candidate.CandidateEntitty;
import br.com.carlosjorge.gestao_vaga.modules.candidate.CandidateRepository;

@Service
public class CreateCandidateUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public CandidateEntitty execute(CandidateEntitty candidateEntitty) {
   

        this.candidateRepository
        .findByUsernameOrEmail(candidateEntitty.getUsername(), candidateEntitty.getEmail())
        .ifPresent((user) -> {
            throw new UserFoundException();
        });

        String password  = passwordEncoder.encode(candidateEntitty.getPassword());
        candidateEntitty.setPassword(password);

        return this.candidateRepository.save(candidateEntitty);

    }

}


