package br.com.consultacep.servico;

import br.com.consultacep.modelos.Endereco;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CriaArquivoService {

    public Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * @description Método responsável por criar um arquivo contendo as informações do CEP consultado.
     *
     * @param enderecoConsultado - Endereco
     */
    public void criaArquivoDeCepConsutado(Endereco enderecoConsultado) {
        try {
            FileWriter escritor = new FileWriter(enderecoConsultado.getCep() + ".json");
            escritor.write(gson.toJson(enderecoConsultado));
            escritor.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao tentar escrever o arquivo de endereços consultados.");
        }
    }
}
