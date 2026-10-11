package br.com.mirella.myproject.controller.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** O que a API aceita ao criar um livro (entrada) */
public class BookCreateRequest {
    @NotBlank(message = "titulo é obrigatório")
    @Size(max = 120, message = "titulo deve ter no máximo 120 caracteres")
    private String titulo;

    @NotBlank(message = "autor é obrigatório")
    private String autor;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
