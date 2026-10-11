package br.com.mirella.myproject;
import br.com.mirella.myproject.controller.BookModelInterface;
import br.com.mirella.myproject.controller.dto.BookCreateRequest;
import br.com.mirella.myproject.controller.dto.BookResponse;
import br.com.mirella.myproject.mapper.BookMapper;
import br.com.mirella.myproject.model.BookModelMysql;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController //significa que será essa classe que vai responder as requisições
@RequestMapping(path = "/books", produces = "application/json") //qualquer mapeamento desse controlador comecara com books (definir qual URL será associada a um controlador ou método)
public class BookController {
    @Autowired
    private final BookModelInterface model; //IoC e DI

    public BookController(@Qualifier("pgModel") //Qualifier vai implementar pelo apelido (pgModel)
            BookModelInterface model) { //Ioc e DI
        this.model = model; //guarda a dependencia recebida
    }

    @GetMapping
    public ResponseEntity<List<Book>> listarLivros(
            @RequestParam(name = "titulo", required = false) String titulo) {

        List<Book> livros = model.listarTodos();

        if (titulo == null || titulo.isBlank()) {
            return ResponseEntity.ok(livros);
        }

        List<Book> filtrados = new ArrayList<>();

        for (Book book : livros) {
            if (book.getTitle().toLowerCase()
                    .contains(titulo.toLowerCase())) {
                filtrados.add(book);
            }
        }

        return ResponseEntity.ok(filtrados);
    }

    @GetMapping("/{id}") //passa id na URL - books/5
    public ResponseEntity<BookResponse> obterPorId(@PathVariable long id){ //PathVariable converte a string da url para long
        List<Book> books = model.listarTodos();
        for (Book book : books){
            if(book.getId() == id){ // Compara o valor do ID do livro com o ID solicitado (long).
                return ResponseEntity.ok(BookMapper.toResponse(book));
            }
        }
        return ResponseEntity.notFound().build();
    }

    //usar consumes e produces quando devolve corpo a requisicao
    @PostMapping(consumes = "application/json")
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookCreateRequest body){ //ResponseEntity - dizer ao Spring exatamente qual código de status e qual conteúdo deve retornar na resposta. @Valid - valida regras aplicadas no DTO antes de executar o metodo
        List<Book> books = model.listarTodos();
        long newId = (long) (books.size() + 1);
        Book bookToSave = BookMapper.toEntity(body, newId);
        books.add(bookToSave);

        return ResponseEntity.ok(BookMapper.toResponse(bookToSave)); //slide indica que é ResponseEntity.body
    }
}
