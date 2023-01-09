##  <img src="https://icon-library.com/images/java-icon-png/java-icon-png-15.jpg" alt="icon" height="40"/> 7DaysOfCode-JAVA 

É um  projeto da alura que visa mostrar como funciona a dinâmica do dia a dia de um programador. A proposta é durante 7 dias enviar tarefas diárias para serem executadas.

##  Projeto - Consumir uma Api  <img src="https://cdn-icons-png.flaticon.com/128/967/967579.png" alt="icon" height="40"/>

O projeto final consiste em uma página web com os dados dos filmes consumidos da APi.

## Habilidades trabalhadas 
São várias as habilidades trabalhadas durante esses sete dias entre hard skills e soft skills.
#### Entre as soft skills vale destacar: 
* Gestão de tempo: por serem desafios diários, é muito importante manter a consistência e não deixar acumular as tarefas.
* Pensamento crítico: durante esses 7 dias fica evidente que o como fazer, é mais importante que o fazer. Antes de escrever o código é muito importante pensar em todo o projeto e ir evoluindo e refatorando o que já não faz mais sentido.
#### Entre as hard skills vale destacar: 
* Orientação a objetos: o  paradigma de orientação a aobjetos é amplamente ultilizado e é muito importante realizar projetos que apliquem os conceitos desse paradigma 
* Consumir api´s: apeasar de existirem n maneiras de fazer iss, neste projeto foi escolhido a forma mais básica para consumir a api com java.net

![icon](https://cdn-icons-png.flaticon.com/128/5486/5486712.png)

## Day 1
### Consumir a API do IMDB! Seu objetivo será imprimir os resultados de uma busca na linha de comando
Obs.: Como tive problemas para gerar a chave da Api do IMDB eu optei por usar a Api do TMDB.
Para realizar o desafio eu usei o pacote do java.net.http
```java
  String tmbdUrl = "https://api.themoviedb.org/3/movie/";
  String url = tmbdUrl + "top_rated" + this.keyApi + "&language=pt-BR";
  URI endereco = URI.create(url);
  var cliente = HttpClient.newHttpClient();
  var request = HttpRequest.newBuilder(endereco).GET().build();
  HttpResponse<String> response = cliente.send(request, BodyHandlers.ofString());
  String json =  response.body();
```
## Day 2
### A sua tarefa será parsear essa resposta. Em outras palavras, você vai extrair as informações desse JSON
Para realizar o desafio usei o Gson do google pela facilidade e  por deixar o código mais legível. Para isso criei duas classes para mapear o json e facilitar trabalhar com os dados
```java
  Gson gson = new Gson();
  ListMovie Filmes = gson.fromJson(json, ListMovie.class);
```
Dentro da classe ListMovie tem um atributo ArrayList que guarda separamente dados de cada filme, mapeando os dados de acordo com a classe Movie que é quem especifica quais dados pegar do json
```java
private ArrayList<Movie> results;
```
[Veja a documentação do Gson](https://github.com/google/gson)
## Day 3
### No desafio de hoje, a ideia será modelar, ou pelo menos iniciar uma modelagem melhor do seu código
Nesse dia tive pouco trabalho pois pelo que fiz no segundo dia o código estava limpo e dividido em classes, também separado por pacotes

## Day 4
### No desafio de hoje, você vai trabalhar com a saída e gerar uma página HTML a partir da lista de objetos que você já tem no seu código Java
Para esse desafio eu usei o java.io.PrintWriter para gerar o arquivo html. Criei uma classe chamada HtmlGenerator e dividi cada parte do html em metodos privados (conteudo e head) e um método público (generate) que monta o html e passa como parâmetro para o método da classe PrintWriter,  gerando assim o html.
```java
  try (PrintWriter printWriter = new PrintWriter(
       new OutputStreamWriter(new FileOutputStream("index.html"), "UTF-8"))) {
    HtmlGenerator GenerateHtml = new HtmlGenerator(printWriter);
    GenerateHtml.generate(Filmes);
  } catch (Exception e) {
    System.err.println("Erro ao gerar o arquivo!");
  }
```
Repare que ao instannciar a classse printWriter passo no construtor parâmetros para obter o encoding para UTF-8. Já classe HtmlGenerator recebe no construtor o Writer e o método público generate o objeto com os filmes (poderia passar tbm o ArrayList de filmes mas optei por não usar essa alternativa). <br>
Para estilização da página html usei o bootstrap [Veja no site](https://getbootstrap.com/docs/4.0/components/card/?utm_source=ActiveCampaign&utm_medium=email&utm_content=%237DaysOfCode+-+Java+4%2F7%3A+%F0%9F%91%A9%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB+Gerando+o+HTML&utm_campaign=%5BAlura+%237Days+Of+Code%5D%28Java%29+Dia+4%2F7%3A+Gerando+o+HTML)
## Day 5
### Encapsular a chamada da API dentro de uma nova classe
Outro dia de pouco trabalho, para isso eu criei um pacote chamado services e dentro uma classe chamada TmdbApiClient que no seu construtor recebe a chave da Api e possui um método para fazer a chamada Http.
```java
 String json = new TmdbApiClient(tmdbkey).getBody();
```
## Day 6
### Implementar uma nova interface que irá definir o comportamento comum de um conteúdo
Criei a interface Content para tornar a gerar o html preparado para ser gerado independente da Api consumida. A interface ficou:
```java
 public interface Content {
  String title();

  String urlImage();

  String rating();

  String year();
}
```
Com a implementação da interface eu achei melhor não utilizar mais a classe ListMovie, deixando para a classe Movie toda a responsabilidade de implementar os métodos da interface.<br>
Obs.: Tambem era pedido para consumir a Api da marvel mas o site estava instavel e não consegui fazer meu cadastro.
## Day 7
### Criar criterios de ordenamento
No ultimo dia o desafio era ordenar a lista de filmes, desafio tranquilo. Para isso usei a classe Collections com seu método sort:
```java
  Collections.sort(Filmes.getResults(), Comparator.comparing(Content::year));
```
A minha ordenação está pelo ano de lançamento, porém, pode-se mudar para outros criterios