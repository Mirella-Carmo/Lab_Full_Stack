package br.com.mirella.myproject.model;

import br.com.mirella.myproject.Book;
import br.com.mirella.myproject.controller.BookModelInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mysqlModel")
public class BookModelMysql implements BookModelInterface {
    @Override
    public List<Book> listarTodos(){
        return null;
    }
}
