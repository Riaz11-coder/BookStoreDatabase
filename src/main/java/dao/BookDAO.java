package dao;

import model.Book;
import java.util.List;

public interface BookDAO {
    void save(Book book);
    Book findById(String isbn);
    List<Book> findAll();
    void update(Book book);
    void delete(String isbn);
}
