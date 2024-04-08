package org.example.controllers;

import lombok.AllArgsConstructor;
import org.example.service.ClientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/clients")
public class ClientsController {
    private ClientService clientService;

    @GetMapping("/getBalance/{id}")
    public  BigDecimal getBalance(@PathVariable("id") Long id){
        return clientService.getBalance(id);
    }

    @Transactional
    @PutMapping("/putMoney/{id}/{sum}")
    public ResponseEntity<String> putMoney(
            @PathVariable("id") Long id,
            @PathVariable("sum") BigDecimal sum){
        return clientService.putMoneу(id, sum);
    }

    @Transactional
    @PutMapping("/takeMoney/{id}/{sum}")
    public ResponseEntity <String> takeMoney(
            @PathVariable("id") Long id,
            @PathVariable("sum") BigDecimal sum){
        return clientService.takeMoney(id, sum);
    }

    @Transactional
    @GetMapping("/listOperation/{id}")
    ResponseEntity getListOperations(
            @PathVariable Long id,
            @RequestParam(value = "beginDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date beginDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getOperationList(id, beginDate, endDate));
    }

    @Transactional
    @GetMapping("/transfer/{sender_id}/{recipient_id}")
    ResponseEntity transferMoneyById(
            @PathVariable(value = "sender_id") Long sender_id,
            @PathVariable(value = "recipient_id") Long recipient_id,
            @RequestParam(value = "sum") BigDecimal sum) {
        return ResponseEntity.status(HttpStatus.OK).body(
                clientService.transferMoney(sender_id, recipient_id, sum));
    }

}
