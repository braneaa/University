package client.Provider;

import web.dto.BookDto;
import web.dto.BooksDto;

public interface BookProvider extends Provider<BookDto> {
    BooksDto getAll();
    BooksDto filterClientsByAuthor(String author);
    BooksDto filterClientsByGenre(String genre);
}
