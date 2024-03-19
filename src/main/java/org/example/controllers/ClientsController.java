package org.example.controllers;

import org.example.model.Client;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientService clientService;
    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;}

    @GetMapping("/getBalance/{clientId}")
    public BigDecimal getBalance(@PathVariable("clientId") Long clientId){
        return clientService.getBalance(clientId);
    }

    @PutMapping("/putMoney/{clientId}/{sum}")
    public ResponseEntity<String> putMoney(@PathVariable("clientId") Long clientId, @PathVariable("sum") BigDecimal sum, Client client){
         return clientService.putMoneу(clientId, sum, client);
    }

    @PutMapping("/takeMoney/{clientId}/{sum}")
    public ResponseEntity<String> takeMoney(@PathVariable("clientId") Long clientId,@PathVariable("sum") BigDecimal sum, Client client){
        return clientService.takeMoney(clientId, sum, client);
    }

}
