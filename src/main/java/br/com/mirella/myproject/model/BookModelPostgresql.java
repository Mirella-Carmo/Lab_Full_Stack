package br.com.mirella.myproject.model;

import br.com.mirella.myproject.Book;
import br.com.mirella.myproject.controller.BookModelInterface;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pgModel")
@Primary //injetar postgre por padrao quando alguem pedir BookModelInterface
public class BookModelPostgresql implements BookModelInterface {
    @Override
    public List<Book> listarTodos(){
        return null;
    }
}
