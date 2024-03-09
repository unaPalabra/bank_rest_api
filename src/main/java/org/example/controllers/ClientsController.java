package org.example.controllers;

import org.example.model.Client;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientService clientService;
    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;}

    @GetMapping("/getBalance/{clientId}")
    public BigDecimal getBalance(@PathVariable("clientId") Long clientId){
        System.out.println(clientService.getBalance(clientId));
        return clientService.getBalance(clientId);
    }
}
