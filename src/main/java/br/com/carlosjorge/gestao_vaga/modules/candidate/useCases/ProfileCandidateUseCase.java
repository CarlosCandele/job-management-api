package br.com.carlosjorge.gestao_vaga.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosjorge.gestao_vaga.modules.candidate.CandidateRepository;
import br.com.carlosjorge.gestao_vaga.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;
    
    public ProfileCandidateResponseDTO execute(UUID idCandidate) {
        var candidate = this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });
        var candidateDTO = ProfileCandidateResponseDTO.builder()
        .description(candidate.getDescription())
        .name(candidate.getName())
        .username(candidate.getUsername())
        .email(candidate.getEmail())
        .id(candidate.getId())
        .build();
        
        return candidateDTO;
    }
}
