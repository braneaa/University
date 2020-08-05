package web.converter;

import core.domain.Sale;
import core.service.BookService;
import core.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.dto.SaleDto;

import java.util.stream.Collectors;
@Component
public class SaleConverter extends BaseConverter<Sale, SaleDto> {

    @Autowired
    private ClientService clientService;

    @Autowired
    private BookService bookService;

    @Override
    public Sale convertDtoToModel(SaleDto dto) {
        Sale sale = Sale.builder()
                .book(bookService.getAllBooks().stream().filter(book -> book.getId().equals(dto.getBookId())).collect(Collectors.toList()).get(0))
                .client(clientService.getAllClients().stream().filter(client -> client.getId().equals(dto.getClientId())).collect(Collectors.toList()).get(0)).build();
        sale.setId(dto.getId());
        return sale;

    }

    @Override
    public SaleDto convertModelToDto(Sale sale) {
        SaleDto dto = SaleDto.builder()
                .bookId(sale.getBook().getId())
                .clientId(sale.getClient().getId())
                .build();
        dto.setId(sale.getId());
        return dto;

    }
}
