package br.com.carlosjorge.gestao_vaga.modules.company.repositoreis;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlosjorge.gestao_vaga.modules.company.entities.JobEntity;

public interface JobRepository  extends JpaRepository<JobEntity, UUID>{
    
    List<JobEntity> findByDescriptionContaining(String filter);
}
