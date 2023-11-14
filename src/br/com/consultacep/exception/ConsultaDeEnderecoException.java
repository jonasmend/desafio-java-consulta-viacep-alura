package br.com.consultacep.exception;

public class ConsultaDeEnderecoException extends RuntimeException {

    private String mensagem;
    public ConsultaDeEnderecoException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
