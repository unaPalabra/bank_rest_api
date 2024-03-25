package org.example.repository;

import org.example.entity.Balance;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


public interface BalanceRepository extends CrudRepository<Balance, Long> {

    @Query(value = "SELECT current_balance FROM balance WHERE client_id=:client_id", nativeQuery = true)
    BigDecimal getBalanceByClient_id(@Param("client_id") Long client_id);

    @Query(value = "SELECT * FROM balance WHERE client_id=:client_id", nativeQuery = true)
    Balance findByClient_id(@Param("client_id") Long client_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE balance SET current_balance =current_balance+:sum WHERE client_id=:client_id", nativeQuery = true)
     void putMoney(@Param("client_id") Long client_id, @Param("sum") BigDecimal sum);

    @Transactional
    @Modifying
    @Query(value = "UPDATE balance SET current_balance =current_balance-:sum WHERE client_id=:client_id", nativeQuery = true)
     void takeMoney(@Param("client_id") Long client_id, @Param("sum") BigDecimal sum);

}