package com.example.demo.dao;

import com.example.demo.model.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("fakeDao")
public class FakeClientDataAccessService implements ClientDao{
    private static List<Client> DB = new ArrayList<>();

    @Override
    public int insertClient(UUID id, Client client) {
        DB.add(new Client(id, client.getName(), client.getSurname()));
        return 1;
    }

    @Override
    public List<Client> selectAllClient() {
        return DB;
    }

    @Override
    public Optional<Client> selectClientById(UUID id) {
        return DB.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Client> selectClientBySurname(String surname) {
        return DB.stream()
                .filter(client -> client.getSurname().equals(surname))
                .collect(Collectors.toList());
    }

    @Override
    public int deleteClientById(UUID id) {
        Optional<Client> clientMaybe = selectClientById(id);
        if (clientMaybe.isPresent()){
            DB.remove(clientMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateClientById(UUID id, Client update) {
        return selectClientById(id)
                .map(client -> {
                    int indexOfClientToUpdate = DB.indexOf(client);
                    if (indexOfClientToUpdate >= 0){
                        DB.set(indexOfClientToUpdate, new Client(id, update.getName(), update.getSurname()));
                        return 1;
                    } return 0;
                })
                .orElse(0);
    }
}
