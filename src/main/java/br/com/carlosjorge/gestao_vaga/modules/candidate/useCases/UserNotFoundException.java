package br.com.carlosjorge.gestao_vaga.modules.candidate.useCases;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
}
