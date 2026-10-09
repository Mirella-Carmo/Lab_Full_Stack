package br.com.mirella.myproject;
import br.com.mirella.myproject.dto.BookCreateRequest;
import br.com.mirella.myproject.dto.BookResponse;
import br.com.mirella.myproject.mapper.BookMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController //significa que será essa classe que vai responder as requisições (é o controlador REST responsável por receber requisições HTTP e retornar resposta)
@RequestMapping("/book") //qualquer mapeamento desse controlador comecara com books (definir qual URL será associada a um controlador ou método)
public class BookController {
    private List<Book> books;

    public BookController(){
        books = new ArrayList<>();
        books.add(new Book("Harry Potter", "JK Rowling"));
        books.add(new Book("Percy Jackson", "Rick Riordan"));
        books.add(new Book("Sherlock Holmes", "Arthur Doyle"));
    }

    @GetMapping //anotação composta que atua como um atalho para: @RequestMapping
    public ResponseEntity<List<Book>> listarLivros(
            @RequestParam(name = "titulo", required = false) String titulo){ //atua na URL base - books/?titulo=Harry Potter
        if (titulo == null || titulo.isBlank()){
            return ResponseEntity.ok(books); //200 com a lista completa
        }

        List<Book> filtrados = new ArrayList<>();
        for (Book book : books){
            if (book.getTitle().toLowerCase().contains(titulo.toLowerCase())){
                filtrados.add(book);
            }
        }
        return ResponseEntity.ok(filtrados); //200 com a lista de filtrados (pode estar vazia)
    }

    @GetMapping("/{id}") //passa id na URL - books/5
    public ResponseEntity<BookResponse> obterPorId(@PathVariable long id){ //PathVariable converte a string da url para long
        for (Book book : books){
            if(book.getId() == id){ // Compara o valor do ID do livro com o ID solicitado (long).
                return ResponseEntity.ok(BookMapper.toResponse(book));
            }
        }
        return ResponseEntity.notFound().build();
    }

    //usar consumes e produces quando devolve corpo a requisicao
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<BookResponse> create(@Valid @RequestBody BookCreateRequest body){ //ResponseEntity - dizer ao Spring exatamente qual código de status e qual conteúdo deve retonrar na resposta. @Valid - valida regras antes de executar o metodo
        long newId = (long) (books.size() + 1);
        Book bookToSave = BookMapper.toEntity(body, newId);
        books.add(bookToSave);

        return ResponseEntity.ok(BookMapper.toResponse(bookToSave)); //slide indica que é ResponseEntity.body
    }
}
