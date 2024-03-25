package org.example.service;

import org.example.entity.Balance;
import org.example.exception.ClientInsufficientFunds;
import org.example.exception.ClientNotFoundException;
import org.example.repository.BalanceRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class BalanceService {
   private final BalanceRepository balanceRepository;

    public BalanceService(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

        public  BigDecimal getBalance(Long client_id){
            Balance balance =balanceRepository.findByClient_id(client_id);
            try{
                if(balance.getClient_id() !=null )
            return balanceRepository.getBalanceByClient_id(client_id);
            }
            catch(NullPointerException e){
                throw new ClientNotFoundException(client_id);
            }
            return null;
        }


    public ResponseEntity<String> putMoneу(Long client_id, BigDecimal sum) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Balance balance =balanceRepository.findByClient_id(client_id);
        try{
            if(balance.getClient_id() !=null )
            balanceRepository.putMoney(client_id, sum.abs());
        }
        catch (NullPointerException e) {
            throw new ClientNotFoundException(client_id);
        }
        return new  ResponseEntity<>("Operation status: Success", headers, HttpStatus.OK);
    }

    public ResponseEntity<String> takeMoney(Long client_id, BigDecimal sum, Balance balance) {
        balance =balanceRepository.findByClient_id(client_id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            if (sum.compareTo(balance.getCurrent_balance())<=0) {
            balanceRepository.takeMoney(client_id, sum.abs());
        }else {
            throw new ClientInsufficientFunds(client_id);
        }
        } catch (NullPointerException e) {
            throw new ClientNotFoundException(client_id);
        }

        return new ResponseEntity<>("Operation status: Success", headers, HttpStatus.OK);
    }

}









