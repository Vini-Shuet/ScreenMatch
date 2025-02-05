package Servicos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Modelos.Titulo;

public class FileService {
	
	public static void salvarListaDeTitulos(List<Titulo> titulos, String caminhoArquivo) throws IOException{
		FileWriter escrita = new FileWriter(caminhoArquivo);
		escrita.write(JsonService.converterObjetoParaJson(titulos));
		escrita.close();
	}
}
