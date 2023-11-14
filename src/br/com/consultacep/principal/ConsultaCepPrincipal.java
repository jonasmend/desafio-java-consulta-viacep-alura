package br.com.consultacep.principal;

import br.com.consultacep.exception.ConsultaDeEnderecoException;
import br.com.consultacep.modelos.Endereco;
import br.com.consultacep.modelos.EnderecoViaCep;
import br.com.consultacep.servico.ConsultaCepService;
import br.com.consultacep.servico.CriaArquivoService;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ConsultaCepPrincipal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        String busca = "";
        final String palavraChaveSair = "sair";
        String mensagemBuscaCep = "Digite o CEP que deseja procurar: ";

        ConsultaCepService consultaCepService = new ConsultaCepService();
        CriaArquivoService criaArquivoService = new CriaArquivoService();

        while (!busca.equalsIgnoreCase(palavraChaveSair)) {
            System.out.println(mensagemBuscaCep);
            busca = leitura.nextLine();

            if(busca.equalsIgnoreCase(palavraChaveSair))
                break;

            try {
                Endereco endereco = consultaCepService.buscaCep(busca);

                if(Objects.nonNull(endereco)) {
                    criaArquivoService.criaArquivoDeCepConsutado(endereco);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Houve um erro ao passar o argumento. Verifique o CEP que digitou - " + busca + ". " + e.getMessage());
            }

        }

    }
}