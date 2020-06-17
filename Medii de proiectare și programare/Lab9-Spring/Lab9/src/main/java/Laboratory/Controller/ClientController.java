package Laboratory.Controller;

import Laboratory.Domain.Client;

import java.util.List;

public interface ClientController {
    
    List<Client> getAllClients();
    void saveClient(Client client);
    void deleteById(Integer id);
    void updateClient(Client client);
    
}
