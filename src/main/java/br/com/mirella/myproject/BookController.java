package br.com.mirella.myproject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private List<Book> books;

    public BookController(){
        books = new ArrayList<>();
        books.add(new Book("Harry Potter", "JK Rowling"));
        books.add(new Book("Percy Jackson", "Rick Riordan"));
        books.add(new Book("Sherlock Holmes", "Arthur Doyle"));
    }

    @GetMapping
    public List<Book> listBooks(){
        return books;
    }
}
