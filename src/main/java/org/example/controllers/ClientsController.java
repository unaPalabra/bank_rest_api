package org.example.controllers;


import org.example.entity.Balance;
import org.example.exception.ClientNotFoundException;
import org.example.repository.BalanceRepository;
import org.example.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final BalanceService balanceService;

    @Autowired
    public ClientsController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping("/getBalance/{clientId}")
    public  BigDecimal getBalance(@PathVariable("clientId") Long clientId){
           return balanceService.getBalance(clientId);
           }

    @PutMapping("/putMoney/{clientId}/{sum}")
    public ResponseEntity<String> putMoney(@PathVariable("clientId") Long clientId, @PathVariable("sum") BigDecimal sum){
         return balanceService.putMoneу(clientId, sum);
    }

    @PutMapping("/takeMoney/{clientId}/{sum}")
    public ResponseEntity<String> takeMoney(@PathVariable("clientId") Long clientId,@PathVariable("sum") BigDecimal sum, Balance balance){
        return balanceService.takeMoney(clientId, sum, balance);
    }

}
