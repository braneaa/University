package core.service;

import core.domain.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    void saveBook(Book book);
    void deleteById(Long id);
    void updateBook(Book book);
    List<Book> filterBooksByAuthor(String author);
    List<Book> filterBooksByGenre(String genre);
}
