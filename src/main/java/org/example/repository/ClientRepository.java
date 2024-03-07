package org.example.repository;

import org.example.model.Client;

import java.util.ArrayList;

public interface ClientRepository {

    ArrayList<Client> getClients();

   Client getClientById(Long client_id);

    Client addClient(Client client);

    Client updateClient(Long client_id, Client client);

    void deleteClient(Long client_id);
}