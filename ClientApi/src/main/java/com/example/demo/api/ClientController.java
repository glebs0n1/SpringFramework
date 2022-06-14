package com.example.demo.api;

import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/client")
@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public void addClient(@Valid @NonNull @RequestBody Client client){
        clientService.addClient(client);
    }

    @GetMapping
    public List<Client> getAllClient(){
        return clientService.getAllClient();
    }

    @GetMapping(path = "{id}")
    public Client getClientById(@PathVariable("id") UUID id){
        return clientService.getClientById(id)
                .orElse(null);
    }

    @GetMapping(path = "surname={surname}")
    public List<Client> getClientBySurname(@PathVariable("surname") String surname){
        return clientService.getClientBySurname(surname);
    }

    @DeleteMapping(path = "{id}")
    public void deleteClientById(@PathVariable("id") UUID id){
        clientService.deleteClient(id);
    }

    @PutMapping(path = "{id}")
    public void updateClient(@PathVariable("id")UUID id,@Valid @NonNull @RequestBody Client clientToUpdate){
        clientService.updateClient(id, clientToUpdate);
    }


}
