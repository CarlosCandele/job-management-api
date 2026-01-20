package br.com.carlosjorge.gestao_vaga.modules.candidate.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosjorge.gestao_vaga.modules.company.entities.JobEntity;
import br.com.carlosjorge.gestao_vaga.modules.company.repositoreis.JobRepository;

@Service
public class ListAllJobsByFilterUseCase {
    

    @Autowired
    private JobRepository jobRepository;

    public List<JobEntity> execute(String filter) {
        return this.jobRepository.findByDescriptionContaining(filter);
    }
}
