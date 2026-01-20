package br.com.carlosjorge.gestao_vaga.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {
    
    @Schema(example = "Desenvolvedor Java")
    private String description;

    @Schema(example = "Carlos Jorge")
    private String name;

    @Schema(example = "Jorge")
    private String username;

    @Schema(example = "carlos@gmail.com")
    private String email;
    
    private UUID id;

}
