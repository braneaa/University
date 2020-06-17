package client.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import web.dto.ClientDto;
import web.dto.ClientsDto;
@Component
public class ClientProviderImpl implements ClientProvider {

    @Value("http://localhost:8080/api/clients")
    private String URL;

    private final RestTemplate restTemplate;

    @Autowired
    public ClientProviderImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ClientsDto getAll() {
        return restTemplate.getForObject(
                URL,
                ClientsDto.class
        );
    }

    @Override
    public ClientsDto filterClientsByName(String name) {
        return restTemplate.getForObject(
                URL + "/filter/name/" + name,
                ClientsDto.class
        );
    }

    @Override
    public void save(ClientDto DTO) {
        restTemplate.postForObject(
                URL,
                DTO,
                ClientsDto.class);
    }

    @Override
    public void update(ClientDto DTO) {
        restTemplate.put(
                URL + "/{id}",
                DTO,
                DTO.getId());
    }

    @Override
    public ClientDto getByID(Long ID) {
        return restTemplate.getForObject(
                URL + "/" + ID,
                ClientDto.class
        );
    }

    @Override
    public void delete(Long ID) {
        restTemplate.delete(
                URL + "/" + ID
        );
    }
}
