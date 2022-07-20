package com;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.*;
import java.util.List;
import java.util.Map;




public class Main {

	public class Colors{
		public static final String green = "\u001B[32m";
		public static final String greenBackground = "\u001B[42m";
		public static final String blue = "\u001B[34m";
		public static final String white = "\u001B[37m";
		public static final String whiteBackground = "\u001B[47m";
		public static final String yellow = "\u001B[33m";
		public static final String reset = "\u001B[0m";
	}



	public static void main(String[] args) {

	try{
//Iniciar uma conexao http e buscar os top 250 filmes
		String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
		URI endpoint = URI.create(url);
		var client = HttpClient.newHttpClient();
		var request = HttpRequest.newBuilder(endpoint).GET().build();
		HttpResponse<String> response;
		String body ;
		response = client.send(request, BodyHandlers.ofString());
		body = response.body();
//Parsear o retorno em uma string
		List<Map<String , String>> moviesList = new JsonParser().parse(body);
//titulo, poster, classificação
		for (Map<String, String> films : moviesList) {
			System.out.println(Colors.green + "Titulo: " + films.get("title") + Colors.reset);
			System.out.println(Colors.blue + "Poster: " + films.get("image") + Colors.reset);
			float clas = Float.parseFloat(films.get("imDbRating"));
			System.out.println(Colors.yellow + "Classficacao: " + clas + "/10");

			System.out.println(Colors.reset);

		}
	} catch (Exception e){}

	}
}