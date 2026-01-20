package br.com.carlosjorge.gestao_vaga.modules.candidate.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carlosjorge.gestao_vaga.modules.candidate.entity.ApplyJobEntity;

public interface ApplyJobRepository  extends JpaRepository<ApplyJobEntity, UUID> {
    

}
