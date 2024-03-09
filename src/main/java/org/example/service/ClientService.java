package org.example.service;

import org.example.model.Client;
import org.example.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;


@Service
public class ClientService implements ClientRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//     @Override
//    public Client getBalance(Long clientId){
//        try{
//           String sql = "SELECT current_balance FROM balance WHERE client_id=?";
//           Client client= jdbcTemplate.queryForObject(sql ,new Object[]{clientId}, new ClientMapper());
//            return client;
//        }catch(Exception e){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
       @Override
    public BigDecimal getBalance(Long clientId){
        try{
           String sql = "SELECT current_balance FROM balance WHERE client_id=?";
           BigDecimal balance= jdbcTemplate.queryForObject(sql, BigDecimal.class ,clientId);
            return balance;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Client putMoneу(Long client_id, BigDecimal sum) {


        return null;
    }

    @Override
    public Client takeMoney(Long client_id, BigDecimal sum) {
        return null;
    }

//    @Override
//    public Client updateClient(Long clientId, Client client){
//        if(client.getClient_id() != null){
//            jdbcTemplate.update("UPDATE balance SET client_id=? WHERE client_id=?",client.getClient_id(), clientId);
//        }
//        if(client.getCurrent_balance() != null){
//            jdbcTemplate.update("UPDATE balance SET current_balance =? WHERE client_id=?",client.getCurrent_balance(),clientId);
//        }
//
//         return getBalance(client.getClient_id());
//    }

}









