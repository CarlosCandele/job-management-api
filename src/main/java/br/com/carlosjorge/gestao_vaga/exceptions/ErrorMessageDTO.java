package br.com.carlosjorge.gestao_vaga.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDTO {
    
    private String message;
    private String fied;
}
