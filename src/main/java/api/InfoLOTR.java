package api;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class InfoLOTR {
	
	public static Frases getFrase() throws IOException, InterruptedException {
		String i = String.valueOf((int) (Math.random() * 2000));
		String url = "https://the-one-api.dev/v2/quote?limit=1&offset=" + i;
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url)).GET()
				.setHeader("Authorization", "Bearer UfziGCL4jFA_fBcB6q9o").build();

		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		String json = response.body();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		Frases info = gson.fromJson(json, Frases.class);
	
		return info;
	}
	
	public static String getAutor(String autorId) throws IOException, InterruptedException {
		String URL = "https://the-one-api.dev/v2/character/" + autorId;
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(URL)).GET()
				.setHeader("Authorization", "Bearer UfziGCL4jFA_fBcB6q9o").build();

		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		String json = response.body();
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		Personajes info = gson.fromJson(json, Personajes.class);
		
		return info.getDocs().get(0).getName();
	
	}
}