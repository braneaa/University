package core.service;

import core.domain.Client;
import core.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientServiceImpl implements ClientService {
    public static final Logger log = LoggerFactory.getLogger(ClientService.class);
    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<Client> getAllClients() {
        log.trace("getAllClients called");
        Iterable<Client> clients =  clientRepository.findAll();
        log.trace("getAll - method finished");
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public void saveClient(Client client) {
        log.trace("saveClient called: client={}",client);
        clientRepository.save(client);
        log.trace("saveClient successfully executed");
    }

    @Override
    public void deleteById(Long id) {
        log.trace("deleteById called: id={}",id);
        clientRepository.deleteById(id);
        log.trace("deleteById successfully executed");
    }

    @Override
    @Transactional
    public void updateClient(Client client) {
        log.trace("updateClient called: client={}",client);
        clientRepository.findById(client.getId())
                .ifPresent(s -> {
                    s.setPhoneNumber(client.getPhoneNumber());
                    s.setName(client.getName());
                    s.setId2(client.getId2());
                });
        log.trace("updateClient successfully executed");

    }

    @Override
    public List<Client> filterClientsByName(String name) {
        log.trace("filterClientsByName - method entered: name={}", name);
        Iterable<Client> clients = clientRepository.findAll();
        log.trace("filterClientsByFirstName - method finished");
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .filter((e)-> e.getName().equals(name))
                .collect(Collectors.toList());
    }
}
