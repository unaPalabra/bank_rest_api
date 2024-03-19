package org.example.service;

import org.example.exception.ClientInsufficientFunds;
import org.example.exception.ClientNotFoundException;
import org.example.model.Client;
import org.example.model.ClientMapper;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class ClientService implements ClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

       @Override
    public BigDecimal getBalance(Long clientId){
        try{
           String sql = "SELECT current_balance FROM balance WHERE client_id=?";
           BigDecimal balance= jdbcTemplate.queryForObject(sql, BigDecimal.class ,clientId);
            return balance;
        }catch(Exception e){
            throw new ClientNotFoundException(clientId);
        }
    }

    @Override
    public ResponseEntity<String> putMoneу(Long client_id, BigDecimal sum, Client client) {
           client = getClientById(client_id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(client.getClient_id() != null) {
            String sql = "UPDATE balance SET current_balance = current_balance + ? WHERE client_id=?";
             jdbcTemplate.update(sql, sum.abs(), client_id );
        }
        return new  ResponseEntity<>("Operation status: Success", headers, HttpStatus.OK);
    }

    @Override
    public Client getClientById(Long clientId){
        try{
            Client client= jdbcTemplate.queryForObject("SELECT * FROM balance WHERE client_id=?"
                    ,new Object[]{clientId}, new ClientMapper());
            return client;
        }catch(Exception e){
            throw new ClientNotFoundException(clientId);
        }
    }

    @Override
    public ResponseEntity<String> takeMoney(Long client_id, BigDecimal sum, Client client) {
        client = getClientById(client_id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(client.getClient_id() != null) {
            if (sum.compareTo(client.getCurrent_balance())<=0) {
                String sql = "UPDATE balance SET current_balance = current_balance - ? WHERE client_id=?";
                jdbcTemplate.update(sql, sum.abs(), client_id);
            }else {
                throw new ClientInsufficientFunds(client_id);
            }
        }
        else {
             throw new ClientNotFoundException(client_id);
        }
        return new ResponseEntity<>("Operation status: Success", headers, HttpStatus.OK);
    }

}









