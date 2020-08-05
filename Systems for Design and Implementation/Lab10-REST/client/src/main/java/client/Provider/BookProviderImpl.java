package client.Provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import web.dto.BookDto;
import web.dto.BooksDto;
import web.dto.ClientsDto;

@Component
public class BookProviderImpl implements BookProvider {

    @Value("http://localhost:8080/api/books")
    private String URL;

    private final RestTemplate restTemplate;

    public BookProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BooksDto getAll() {
        return restTemplate.getForObject(
                URL,
                BooksDto.class
        );
    }

    @Override
    public BooksDto filterClientsByAuthor(String author) {
        return restTemplate.getForObject(
                URL + "/filter/author/" + author,
                BooksDto.class
        );
    }

    @Override
    public BooksDto filterClientsByGenre(String genre) {
        return restTemplate.getForObject(
                URL + "/filter/genre/" + genre,
                BooksDto.class
        );
    }

    @Override
    public void save(BookDto DTO) {
        restTemplate.postForObject(
                URL,
                DTO,
                BooksDto.class
        );
    }

    @Override
    public void update(BookDto DTO) {
        restTemplate.put(
                URL + "/{id}",
                DTO,
                DTO.getId()
        );
    }

    @Override
    public BookDto getByID(Long ID) {
        return restTemplate.getForObject(
                URL + "/" + ID,
                BookDto.class
        );
    }

    @Override
    public void delete(Long ID) {
        restTemplate.delete(URL + "/" + ID);
    }
}
