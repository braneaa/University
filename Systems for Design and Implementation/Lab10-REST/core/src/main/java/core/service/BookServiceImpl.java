package core.service;

import core.domain.Book;
import core.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImpl implements BookService {

    public static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        log.trace("getAllBooks called");
        Iterable<Book> books = bookRepository.findAll();
        log.trace("getAllBooks method finished");
        return StreamSupport.stream(
                books.spliterator(),false)
                .collect(Collectors.toList());
    }

    @Override
    public void saveBook(Book book) {
        log.trace("SaveBook method called: book = {}" , book);
        bookRepository.save(book);
        log.trace("saveBook method finished");
    }

    @Override
    public void deleteById(Long id) {
        log.trace("deleteById called: id{}", id);
        bookRepository.deleteById(id);
        log.trace("deleteById method finished");
    }

    @Override
    public void updateBook(Book book) {
        log.trace("updateBook method called: book = {}" , book);
        bookRepository.findById(book.getId())
                .ifPresent(book1 -> {
                    book1.setAuthor(book.getAuthor());
                    book1.setGenre(book.getGenre());
                    book1.setName(book.getName());
                    book1.setSerialNumber(book.getSerialNumber());
                });
        log.trace("updateBook method finished");
    }

    @Override
    public List<Book> filterBooksByAuthor(String author) {
        log.trace("filterBooksByAuthor method called : author = {}" ,author);
        Iterable<Book> books = bookRepository.findAll();
        log.trace("filterBooksByAuthor method finished");
        return StreamSupport.stream(
                books.spliterator(),false
        ).filter((e) -> e.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> filterBooksByGenre(String genre) {
        log.trace("filterBooksByGenre method called : author = {}" ,genre);
        Iterable<Book> books = bookRepository.findAll();
        log.trace("filterBooksByGenre method finished");
        return StreamSupport.stream(
                books.spliterator(),false
        ).filter((e) -> e.getGenre().equals(genre))
                .collect(Collectors.toList());
    }
}
