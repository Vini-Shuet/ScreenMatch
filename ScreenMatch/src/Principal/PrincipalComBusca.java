package Principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Modelos.Titulo;
import Modelos.TituloOMDB;
import Servicos.ApiService;
import Servicos.JsonService;
import Servicos.FileService;
import Excecao.ErroDeConversaoDeAnoException;

public class PrincipalComBusca {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner teclado = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        String busca = "";

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.print("Digite um filme para busca: ");
            busca = teclado.nextLine().trim();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            String endereco = "http://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=4600a754";
            System.out.println("Buscando: " + endereco);

            try {
                String json = ApiService.buscarFilme(endereco);
                TituloOMDB meuTituloOmdb = JsonService.converterJsonParaOMDB(json);
                Titulo meuTitulo = new Titulo(meuTituloOmdb);

                titulos.add(meuTitulo);
                System.out.println("Filme encontrado: " + meuTitulo);
            } catch (NumberFormatException e) {
                System.out.println("Erro de conversão numérica: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro na busca: " + e.getMessage());
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Filmes coletados: " + titulos);
        FileService.salvarListaDeTitulos(titulos, "filmes.json");
        System.out.println("Lista salva com sucesso.");
    }
}
