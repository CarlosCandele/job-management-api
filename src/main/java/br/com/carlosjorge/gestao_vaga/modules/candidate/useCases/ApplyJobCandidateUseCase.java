package br.com.carlosjorge.gestao_vaga.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosjorge.gestao_vaga.exceptions.JobNotFoundException;
import br.com.carlosjorge.gestao_vaga.modules.candidate.CandidateRepository;
import br.com.carlosjorge.gestao_vaga.modules.candidate.entity.ApplyJobEntity;
import br.com.carlosjorge.gestao_vaga.modules.candidate.repository.ApplyJobRepository;
import br.com.carlosjorge.gestao_vaga.modules.company.repositoreis.JobRepository;

@Service
public class ApplyJobCandidateUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;
    //ID do Candidato
    //ID da vaga
    public ApplyJobEntity execute(UUID idCandidate, UUID idJob) {
        // Validar se o candidato existe
        this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        // Validar se a vaga existe
        this.jobRepository.findById(idJob)
        .orElseThrow(() -> {
            throw new JobNotFoundException();
        });

        // Candidato se increver na vaga
        var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob).build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}
