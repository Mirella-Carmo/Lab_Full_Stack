package br.com.mirella.myproject.dto;

/** O que a API devolve para o cliente (saída) */
public class BookResponse {
    private Long id;
    private String title; // note: podemos padronizar nomes
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}