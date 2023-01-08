import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import com.google.gson.Gson;

import domain.Movie;
import pages.HtmlGenerator;
import services.TmdbApiClient;

public class App {
    public static void main(String[] args) throws Exception {
        String tmdbkey = "?api_key=4743b8e8d156e1dc6c97ea66e3571e68";

        String json = new TmdbApiClient(tmdbkey).getBody();

        // Criando Objeto/ modelando para o tipo Movie
        Gson gson = new Gson();
        Movie Filmes = gson.fromJson(json, Movie.class);

        // Gerando arquivo html com dados da lista de filmes
        try (PrintWriter printWriter = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream("index.html"), "UTF-8"))) {
            new HtmlGenerator(printWriter).generate(Filmes);

        } catch (Exception e) {
            System.err.println("Erro ao gerar o arquivo!");
        }
    }
}