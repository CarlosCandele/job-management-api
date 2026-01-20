package br.com.carlosjorge.gestao_vaga.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Company Not found");
    }
}
