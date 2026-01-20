package br.com.carlosjorge.gestao_vaga.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found");
    }
    
}
