package com.example.demo.service;

import com.example.demo.dao.ClientDao;
import com.example.demo.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientService(@Qualifier("fakeDao") ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public int addClient(Client client){
        return clientDao.insertClient(client);
    }

    public List<Client> getAllClient(){
        return clientDao.selectAllClient();
    }

    public Optional<Client> getClientById(UUID id){
        return clientDao.selectClientById(id);
    }

    public List<Client> getClientBySurname(String surname){ return clientDao.selectClientBySurname(surname);}

    public int deleteClient(UUID id){
        return clientDao.deleteClientById(id);
    }

    public int updateClient(UUID id, Client newClient){
        return clientDao.updateClientById(id, newClient);
    }


}
