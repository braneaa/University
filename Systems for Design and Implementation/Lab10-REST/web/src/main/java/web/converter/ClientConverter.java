package web.converter;

        import core.domain.Client;
        import org.springframework.stereotype.Component;
        import web.dto.ClientDto;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = Client.builder()
                .id2(dto.getId2())
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = ClientDto.builder()
                .id2(client.getId2())
                .name(client.getName())
                .phoneNumber(client.getPhoneNumber())
                .build();
        dto.setId(client.getId());
        return dto;
    }
}
