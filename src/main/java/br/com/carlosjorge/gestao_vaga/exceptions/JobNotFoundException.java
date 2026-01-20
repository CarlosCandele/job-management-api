package br.com.carlosjorge.gestao_vaga.exceptions;

public class JobNotFoundException  extends RuntimeException{
    public JobNotFoundException() {
        super("Job not found");
    }

}
