package Servicos;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Modelos.TituloOMDB;

public class JsonService {

	private static final Gson gson = new GsonBuilder()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
			.setPrettyPrinting()
			.create();
	
	public static TituloOMDB converterJsonParaOMDB(String json) {
		return gson.fromJson(json, TituloOMDB.class);
	}
	
	public static String converterObjetoParaJson(Object objeto) {
        return gson.toJson(objeto);
    }
}
