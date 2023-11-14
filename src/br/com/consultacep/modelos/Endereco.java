package br.com.consultacep.modelos;

public class Endereco implements Comparable<Endereco> {
    String cep;
    String endereco;
    String complemento;
    String bairro;
    String cidade;
    String estado;

    public Endereco() {}

    public Endereco(EnderecoViaCep enderecoViaCep) {
        this.cep = enderecoViaCep.cep();
        this.endereco = enderecoViaCep.logradouro();
        this.complemento = enderecoViaCep.complemento();
        this.bairro = enderecoViaCep.bairro();
        this.cidade = enderecoViaCep.localidade();
        this.estado = enderecoViaCep.uf();
    }

    public String getCep() {
        return cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "(" + this.cep + ") " + this.endereco + ". " + this.cidade + "-" + this.estado;
    }

    @Override
    public int compareTo(Endereco endereco) {
        return this.cep.compareTo(endereco.cep);
    }

    public void exibirInformacoes() {
        System.out.println("""
                \n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
                Informações do CEP %s
                Rua: %s - %s
                Bairro: %s
                Cidade: %s - %s
                =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n
                """.formatted(this.cep, this.endereco, this.complemento, this.bairro, this.cidade, this.estado));
    }
}
