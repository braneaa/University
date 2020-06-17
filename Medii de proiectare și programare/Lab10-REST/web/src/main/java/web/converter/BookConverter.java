package web.converter;

        import core.domain.Book;
        import org.springframework.stereotype.Component;
        import web.dto.BookDto;

@Component
public class BookConverter extends BaseConverter<Book, BookDto> {
    @Override
    public Book convertDtoToModel(BookDto dto) {
        Book book = Book.builder()
                .serialNumber(dto.getSerialNumber())
                .name(dto.getName())
                .author(dto.getAuthor())
                .genre(dto.getGenre())
                .build();
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto dto = BookDto.builder()
                .serialNumber(book.getSerialNumber())
                .name(book.getName())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .build();
        dto.setId(book.getId());
        return dto;
    }
}
