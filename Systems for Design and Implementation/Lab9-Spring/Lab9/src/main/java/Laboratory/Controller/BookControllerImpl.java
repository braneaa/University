package Laboratory.Controller;

import Laboratory.Domain.Book;
import Laboratory.Repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookControllerImpl implements BookController {
    public static final Logger log= LoggerFactory.getLogger(BookController.class);
    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Book> getAllBooks() {
        log.trace("getAllBooks called");
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {
        log.trace("saveBook called: book={}",book);
        bookRepository.save(book);
        log.trace("addBook successfully executed");
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        log.trace("updateBook called: book={}",book);
        bookRepository.findById(book.getId())
                .ifPresent(book1 -> {
                    book1.setAuthor(book.getAuthor());
                    book1.setGenre(book.getGenre());
                    book1.setName(book.getName());
                    book1.setSerialNumber(book.getSerialNumber());

                });
        log.trace("updateBook successfully executed");
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("deleteBook called: id={}",id);
        bookRepository.deleteById(id);
        log.trace("deleteBook successfully executed");
    }

    @Override
    public List<Book> genreFilter(String genre) {
        log.trace("genreFilter called: genre={}",genre);
        Iterable<Book> bookIterator =bookRepository.findAll();
        return StreamSupport.stream(bookIterator.spliterator(),false).filter((g) -> g.getGenre().equals(genre)).collect(Collectors.toList());
    }
}
