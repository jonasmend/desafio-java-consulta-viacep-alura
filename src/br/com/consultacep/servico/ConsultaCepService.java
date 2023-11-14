package br.com.consultacep.servico;

import br.com.consultacep.exception.ConsultaDeEnderecoException;
import br.com.consultacep.modelos.Endereco;
import br.com.consultacep.modelos.EnderecoViaCep;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ConsultaCepService {

    private Gson gson = new GsonBuilder().create();
    private final String enderecoConsultaViaCep = "https://viacep.com.br/ws/%s/json/";
    private final String cepRegex = "[0-9]{5}-?[0-9]{3}";

    public Endereco buscaCep(String cep) {
        Endereco endereco = new Endereco();

        if(!cep.matches(cepRegex))
            throw new IllegalArgumentException("CEP inválido!");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(enderecoConsultaViaCep.formatted(cep)))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String respostaJsonViaCep = response.body();

            if(respostaJsonViaCep.contains("Http 400"))
                throw new ConsultaDeEnderecoException("A consulta no endereço falhou!");

            EnderecoViaCep enderecoViaCep = gson.fromJson(respostaJsonViaCep, EnderecoViaCep.class);
            endereco = new Endereco(enderecoViaCep);
            endereco.exibirInformacoes();

            return endereco;
        } catch (ConsultaDeEnderecoException e) {
            System.out.println(e.getMessage() + enderecoConsultaViaCep.formatted(cep));
        } catch (IllegalStateException e) {
            System.out.println("Erro ao converter resposta da consulta.");
        } catch (IllegalArgumentException e) {
            System.out.println("Houve um erro ao passar o argumento. Verifique o CEP que digitou - "+cep+". " + e.getMessage());
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao buscar cep: " + e.getMessage());
        }

        return null;
    }

}
