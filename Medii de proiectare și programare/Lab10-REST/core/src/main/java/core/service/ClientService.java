package core.service;

import core.domain.Client;

import java.util.List;

public interface ClientService {
    
    List<Client> getAllClients();
    void saveClient(Client client);
    void deleteById(Long id);
    void updateClient(Client client);
    List<Client> filterClientsByName(String name);
    
}
