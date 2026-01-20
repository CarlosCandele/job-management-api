package br.com.carlosjorge.gestao_vaga.modules.candidate.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CreateCandidateDTO(

   @Schema(example = "Santher Jorge")
   @NotBlank
   String name,

   @Schema(example ="Jorge")
   @NotBlank
   @Pattern(regexp= "\\S+", message = "O campo [username] não deve conter espaços")
   String username,

   @Schema(example = "santher@gmail.com")
   @Email
   String email,

   @Schema(example = "admin@12568")
   @Length(min = 10, max = 100)
   String password,

   @Schema(example = "Dev")
   String description,

   @Schema(example = "Desenvolvedor ront-end e back-end")
   String curriculum

) {}
