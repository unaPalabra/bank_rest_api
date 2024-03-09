package org.example.repository;

import org.example.model.Client;

import java.math.BigDecimal;

public interface ClientRepository {

    BigDecimal getBalance(Long client_id);
   Client putMoneу (Long client_id,  BigDecimal sum);
   Client takeMoney (Long client_id,  BigDecimal sum);

}