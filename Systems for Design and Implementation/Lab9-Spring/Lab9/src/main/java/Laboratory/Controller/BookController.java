package Laboratory.Controller;

import Laboratory.Domain.Book;

import java.util.List;
import java.util.Set;

public interface BookController {

    List<Book> getAllBooks();
    void saveBook(Book book);
    void updateBook(Book book);
    void deleteById(Integer id);
    List<Book> genreFilter(String genre);

}
