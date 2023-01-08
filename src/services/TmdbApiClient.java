package services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class TmdbApiClient {
  private String keyApi;

  public TmdbApiClient(String keyApi) {
    this.keyApi = keyApi;
  }

  public String getBody() throws IOException, InterruptedException {
    String tmbdUrl = "https://api.themoviedb.org/3/movie/";
    String url = tmbdUrl + "top_rated" + this.keyApi + "&language=pt-BR";// pegando lista com 20 filmes
    URI endereco = URI.create(url);
    var cliente = HttpClient.newHttpClient();
    var request = HttpRequest.newBuilder(endereco).GET().build();
    HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
    return response.body();
  }
}
