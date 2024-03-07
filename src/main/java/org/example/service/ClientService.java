package org.example.service;

import org.example.model.ClientMapper;
import org.example.model.Client;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientService implements ClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<Client> getClients(){
        List<Client> clientsList = jdbcTemplate.query("SELECT * FROM balance",new ClientMapper());
        ArrayList<Client>clients = new ArrayList<>(clientsList);
        return clients;
    }

    @Override
    public Client getClientById(Long clientId){
        try{
            Client client= jdbcTemplate.queryForObject("SELECT * FROM balance WHERE client_id=?"
                    ,new Object[]{clientId}, new ClientMapper());
            return client;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        
    }

    @Override
    public Client addClient(Client client){
        jdbcTemplate.update("INSERT INTO balance(id, client_id,current_balance, date_operation) VALUES(?,?,?,?)",client.getId(), client.getClient_id(), client.getCurrent_balance(), client.getDate_operation());
        Client savedClient = jdbcTemplate.queryForObject("SELECT * FROM balance WHERE client_id=?",new ClientMapper(),client.getClient_id());
        return savedClient;
    }

    @Override
    public Client updateClient(Long clientId, Client client){
        if(client.getClient_id() != null){
            jdbcTemplate.update("UPDATE balance SET client_id=? WHERE client_id=?",client.getClient_id(), clientId);
        }
        if(client.getCurrent_balance() != null){
            jdbcTemplate.update("UPDATE balance SET current_balance =? WHERE client_id=?",client.getCurrent_balance(),clientId);
        }

         return getClientById(client.getClient_id());
    }

    @Override
    public void deleteClient(Long client_id) {
        jdbcTemplate.update("DELETE FROM balance WHERE client_id=?",client_id);
    }

}









