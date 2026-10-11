package br.com.mirella.myproject.mapper;

import br.com.mirella.myproject.Book;
import br.com.mirella.myproject.controller.dto.BookResponse;
import br.com.mirella.myproject.controller.dto.BookCreateRequest;

public class BookMapper {
    private BookMapper() {}

    public static Book toEntity(BookCreateRequest req, Long newId) {
        Book b = new Book();
        b.setId(newId);
        b.setTitle(req.getTitulo());
        b.setAuthor(req.getAutor());
        return b;
    }

    public static BookResponse toResponse(Book b) {
        BookResponse r = new BookResponse();
        r.setId(b.getId());
        r.setTitle(b.getTitle());
        r.setAuthor(b.getAuthor());
        return r;
    }
}
