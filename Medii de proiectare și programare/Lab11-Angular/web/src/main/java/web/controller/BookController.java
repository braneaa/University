package web.controller;


import core.domain.Book;
import core.domain.Client;
import core.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.BookConverter;
import web.dto.BookDto;
import web.dto.BooksDto;
import web.dto.ClientDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private BookConverter bookConverter;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    List<BookDto> getBooks(){
        log.trace("getBooks - method entered");
        List<Book> books = bookService.getAllBooks();
        return new ArrayList<>(bookConverter.convertModelsToDtos(books));
    }

    @RequestMapping(value = "/books/filter/author/{author}", method = RequestMethod.GET)
    BooksDto filterBooksByAuthor(@PathVariable String author){
        log.trace("filterBooksByAuthor method called: author = {}", author);

        return new BooksDto(bookConverter.convertModelsToDtos(bookService.filterBooksByAuthor(author)));
    }

    @RequestMapping(value = "/books/filter/genre/{genre}", method = RequestMethod.GET)
    BooksDto filterBooksByGenre(@PathVariable String genre){
        log.trace("filterBooksByGenre method called: author = {}", genre);

        return new BooksDto(bookConverter.convertModelsToDtos(bookService.filterBooksByGenre(genre)));
    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    void saveBook(@RequestBody BookDto bookDto)
    {
        log.trace("saveBook method called : bookDTO={}" , bookDto);
        bookService.saveBook(bookConverter.convertDtoToModel(bookDto));
        log.trace("saveBook method finished");
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    void updateBook(@PathVariable Long id, @RequestBody BookDto bookDto){
        log.trace("updateBook method called : id = {}, bookDto = {}", id, bookDto);
        bookDto.setId(id);
        bookService.updateBook(bookConverter.convertDtoToModel(bookDto));
        log.trace("updateClient method finished");
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        log.trace("deleteClient method called : id = {}" , id);
        bookService.deleteById(id);
        log.trace("deleteClient method finished");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
