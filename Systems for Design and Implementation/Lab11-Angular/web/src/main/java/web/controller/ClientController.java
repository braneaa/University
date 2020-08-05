package web.controller;

import core.domain.Client;
import core.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.BaseConverter;
import web.converter.ClientConverter;
import web.dto.ClientDto;
import web.dto.ClientsDto;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClientController {
    private static final Logger log= LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    List<ClientDto> getClients(){
        log.trace("getClients - method entered");
        List<Client> clients = clientService.getAllClients();
        return new ArrayList<>(clientConverter.convertModelsToDtos(clients));
    }

    @RequestMapping(value = "/clients/filter/name/{name}", method = RequestMethod.GET)
    ClientsDto filterClientsByName(@PathVariable String name){
        log.trace("filterClientsByFirstName - method entered: name={}", name);

        return new ClientsDto(
                clientConverter.convertModelsToDtos(
                        clientService.filterClientsByName(name)
                )
        );
    }

    @RequestMapping(value = "/clients", method =  RequestMethod.POST)
    void saveClient(@RequestBody ClientDto clientDto){
        log.trace("saveClient - method entered: clientDTO={}", clientDto);
        clientService.saveClient(clientConverter.convertDtoToModel(clientDto));
        log.trace("save Client - method finished");
    }

    @RequestMapping(value = "/clients/{id}", method =  RequestMethod.PUT)
    void updateClient(@PathVariable Long id, @RequestBody ClientDto clientDto){
        log.trace("updateClient - method entered: id={}, clientDTO={}", id, clientDto);

        clientDto.setId(id);
        clientService.updateClient(
                clientConverter.convertDtoToModel(clientDto)
        );

        log.trace("updateClient - method finished");
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id){
        log.trace("deleteClient - method entered: id={}", id);

        clientService.deleteById(id);

        log.trace("deleteClient - method finished");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
