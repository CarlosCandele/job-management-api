package br.com.carlosjorge.gestao_vaga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Gestão de Vagas", description = "API responsável pela gestão de vagas", version = "1"))
//@SecurityScheme(name = "jwt_auth", scheme = "bearer", bearerFormat = "JWT", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class GestaoVagaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoVagaApplication.class, args);
	}

}
