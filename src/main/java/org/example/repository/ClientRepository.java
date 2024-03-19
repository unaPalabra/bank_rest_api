package org.example.repository;

import org.example.model.Client;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface ClientRepository {
    Client getClientById(Long client_id);
    BigDecimal getBalance(Long client_id);
    ResponseEntity<String>  putMoneу(Long client_id, BigDecimal sum, Client client);
    ResponseEntity<String> takeMoney (Long client_id, BigDecimal sum, Client client);
}