package br.com.carlosjorge.gestao_vaga.modules.candidate.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthCandidateResponseDTO {
    
    @JsonProperty("access_token")
    private String access_token;
    private Long expires_in;
    private List<String> roles;
}
