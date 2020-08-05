package client.Provider;

import web.dto.ClientDto;
import web.dto.ClientsDto;

public interface ClientProvider extends Provider<ClientDto> {
    ClientsDto getAll();
    ClientsDto filterClientsByName(String name);
}
