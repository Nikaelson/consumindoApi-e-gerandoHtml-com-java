package domain;

import java.util.ArrayList;

import models.Content;

public class Movie implements Content {
  // A classe Movie é uma abstração de como queremos que os dados da Api, veja
  // que deelimita a 4 campos*/

  private String release_date; // Data delançamento
  private String title;// titulo (adaptado para português)
  private String original_title;// titulo origial
  private String poster_path; // imagem (pôster)
  private ArrayList<Movie> results; // vai guardar o endereço de cada objeto filme

  @Override
  public String title() {
    return this.title;
  }

  @Override
  public String urlImage() {
    return this.poster_path;
  }

  @Override
  public String rating() {
    // votos não quis iimplementar
    return null;
  }

  @Override
  public String year() {
    return this.release_date;
  }

  public String originalTitle() {
    return this.original_title;
  }

  @Override
  public String toString() {
    return "Titulo: " + this.title() + " , Ano: " + this.year() + " , urlImagem: " + this.urlImage();
  }

  public ArrayList<Movie> getResults() {
    return results;
  }
}
