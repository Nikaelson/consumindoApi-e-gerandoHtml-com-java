package pages;

import java.io.IOException;
import java.io.PrintWriter;

import domain.Movie;

public class HtmlGenerator {
  private PrintWriter printWriter;

  public HtmlGenerator(PrintWriter printWriter) {
    this.printWriter = printWriter;
  }

  private String conteudo(Movie movies) {
    String conteudo = "";
    for (Movie movie : movies.getResults()) {
      conteudo += "<div class=\"card text-white bg-dark mb-3\" style=\"max-width:18rem;flex: 1 0 100%;\"><h4 class=\"card-header\">"
          + movie.title() + "</h4><div class=\"card-body\"><img class=\"card-img\"src=\"https://image.tmdb.org/t/p/w500"
          + movie.urlImage()
          + "\" alt=\"kael poster\"><p class=\"card-text mt-2\">Lançamento " +
          movie.year()
          + "</p></div></div>";
    }
    return conteudo;
  }

  private String header() {
    String head = "<head><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">"
        + "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css\"+"
        + "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\"></head>";
    return head;
  }

  public String generate(Movie movies) throws IOException {
    String html = "<!DOCTYPE html><html lang=" + "pt-BR" + ">" + this.header()
        + "<body><div class=\"card-deck d-flex align-content-center justify-content-center flex-wrap\">"
        + this.conteudo(movies)
        + "</div></body></html>";

    this.printWriter.println(html);
    return html;
  }

}
