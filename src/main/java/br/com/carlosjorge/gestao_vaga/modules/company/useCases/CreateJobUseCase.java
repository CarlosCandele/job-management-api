package br.com.carlosjorge.gestao_vaga.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.carlosjorge.gestao_vaga.exceptions.CompanyNotFoundException;
import br.com.carlosjorge.gestao_vaga.modules.company.entities.JobEntity;
import br.com.carlosjorge.gestao_vaga.modules.company.repositoreis.CompanyRepository;
import br.com.carlosjorge.gestao_vaga.modules.company.repositoreis.JobRepository;

@Service
public class CreateJobUseCase {
    
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });

        return this.jobRepository.save(jobEntity);
    }
}
