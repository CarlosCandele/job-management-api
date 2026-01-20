package br.com.carlosjorge.gestao_vaga.modules.company.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.carlosjorge.gestao_vaga.modules.company.dto.CreateJobDTO;
import br.com.carlosjorge.gestao_vaga.modules.company.entities.JobEntity;
import br.com.carlosjorge.gestao_vaga.modules.company.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas", description = "Informações das vagas")
    @Operation(summary = "Cadastro de vagas", description = "Essa função é responsável por cadastrar as vagas dentro da empresa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = JobEntity.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public ResponseEntity<JobEntity> create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {
        // Pega o company_id que foi colocado no request pelo SecurityCompanyFilter
        var companyIdAttr = request.getAttribute("company_id");

        if (companyIdAttr == null) {
            return ResponseEntity.status(401).build(); // não autenticado
        }

        UUID companyId;
        try {
            companyId = UUID.fromString(companyIdAttr.toString());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null); // ID inválido
        }

        var jobEntity = JobEntity.builder()
                .companyId(companyId)
                .description(createJobDTO.getDescription())
                .level(createJobDTO.getLevel())
                .benefits(createJobDTO.getBenefits())
                .build();

        var createdJob = createJobUseCase.execute(jobEntity);
        return ResponseEntity.ok(createdJob);
    }
}
