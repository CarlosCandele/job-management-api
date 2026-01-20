package br.com.carlosjorge.gestao_vaga.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import br.com.carlosjorge.gestao_vaga.exceptions.JobNotFoundException;
import br.com.carlosjorge.gestao_vaga.modules.candidate.CandidateEntitty;
import br.com.carlosjorge.gestao_vaga.modules.candidate.CandidateRepository;
import br.com.carlosjorge.gestao_vaga.modules.candidate.entity.ApplyJobEntity;
import br.com.carlosjorge.gestao_vaga.modules.candidate.repository.ApplyJobRepository;
import br.com.carlosjorge.gestao_vaga.modules.company.entities.JobEntity;
import br.com.carlosjorge.gestao_vaga.modules.company.repositoreis.JobRepository;


@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTeste {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;
    
    @Test
    @DisplayName("should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }
    @Test
    @DisplayName("should not be able apply job with job not found")
    public void should_not_be_able_apply_job_with_job_not_found() {
        var idCandidate = UUID.randomUUID();

        var candidate = new CandidateEntitty();
        candidate.setId(idCandidate);

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    public void should_be_able_to_create_a_new_apply_job() {
        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate)
        .jobId(idJob)
        .build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntitty()));
        when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);
        
        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());

    }
}
