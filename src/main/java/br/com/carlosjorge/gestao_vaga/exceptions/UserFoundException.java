package br.com.carlosjorge.gestao_vaga.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException() {
        super("Usuário ja existe");
    }
}
