package org.example.controllers;

import org.example.model.Client;
import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientService clientService;
    @Autowired
    public ClientsController(ClientService clientService) {
        this.clientService = clientService;}


    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable("clientId") Long clientId){
        clientService.deleteClient(clientId);
    }

    @PutMapping("/{clientId}")
    public Client updateClient(@PathVariable("clientId") Long clientId,@RequestBody Client client){
        return clientService.updateClient(clientId, client);
    }

    @GetMapping("/{clientId}")
    public Client getClientById(@PathVariable("clientId") Long clientId){
        return clientService.getClientById(clientId);
    }

    @GetMapping()
    public ArrayList<Client> getClients(){
        System.out.println(clientService.getClients());
        return clientService.getClients();
    }

    @PostMapping()
    public Client addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

}
