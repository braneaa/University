package Laboratory.Controller;

import Laboratory.Domain.Client;
import Laboratory.Repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientControllerImpl implements ClientController{
    public static final Logger log = LoggerFactory.getLogger(ClientController.class);
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients called");
        return clientRepository.findAll();
    }

    @Override
    public void saveClient(Client client) {
        log.trace("saveClient called: client={}",client);
        clientRepository.save(client);
        log.trace("saveClient successfully executed");
    }

    @Override
    public void deleteById(Integer id) {
        log.trace("deleteById called: id={}",id);
        clientRepository.deleteById(id);
        log.trace("deleteById successfully executed");
    }

    @Override
    @Transactional
    public void updateClient(Client client) {
        log.trace("updateClient called: client={}",client);
        clientRepository.findById(client.getID()).ifPresent(client1 -> {
            client1.setID(client.getID());
            client1.setName(client.getName());
            client1.setPhoneNumber(client.getPhoneNumber());
        });
        log.trace("updateClient successfully executed");
    }
}
