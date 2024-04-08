package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.entity.Client;
import org.example.entity.Operation;
import org.example.exception.ClientInsufficientFunds;
import org.example.exception.ClientNotFoundException;
import org.example.repository.ClientRepository;
import org.example.repository.OperationRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {
   private ClientRepository clientRepository;
   private OperationRepository operationRepository;

    public ClientService(ClientRepository clientRepository, OperationRepository operationRepository) {
        this.clientRepository = clientRepository;
        this.operationRepository = operationRepository;
    }

    public BigDecimal getBalance(Long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new  ClientNotFoundException(id));
        return client.getBalance();
    }
    @Transactional
    public ResponseEntity<String> putMoneу(Long id, BigDecimal sum) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new  ClientNotFoundException(id));
        client.setBalance(client.getBalance().add(sum.abs()));
        clientRepository.save(client);

        Operation operation = new Operation();
        operation.setClient(client);
        operation.setType_operation(1);
        operation.setAmount(sum);
        operation.setTime_operation(new Date());
        operationRepository.save(operation);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new  ResponseEntity<>("Operation status: Success", headers, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> takeMoney(Long id, BigDecimal sum) {
       Client client = clientRepository.findById(id).orElseThrow(() -> new  ClientNotFoundException(id));

       if (sum.compareTo(client.getBalance())<=0) {
            client.setBalance(client.getBalance().subtract(sum.abs()));
            clientRepository.save(client);
        }else {
            throw new ClientInsufficientFunds(id);
        }

        Operation operation = new Operation();
        operation.setClient(client);
        operation.setType_operation(2);
        operation.setAmount(sum.negate());
        operation.setTime_operation(new Date());
        operationRepository.save(operation);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>("Operation status: Success", headers, HttpStatus.OK);
    }

    @Transactional
    public  List<String> getOperationList(Long id, Date beginDate, Date endDate) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new  ClientNotFoundException(id));
        List<String> result = new ArrayList<>();

        if (beginDate == null || endDate == null) {
            List<Operation> list = operationRepository.findOperationsByClientId(client.getId());
            for (Operation e : list) {
                result.add("Amount: " + String.valueOf(e.getAmount()));
                result.add("Type operation: " + String.valueOf(e.getType_operation()));
                result.add("Time operation: " + String.valueOf(e.getTime_operation()));
            }
            return result;
        } else {
            List<Operation> listRange = operationRepository.findOperationsByClientIdAndDateRange(client.getId(), beginDate, endDate);
            for (Operation e : listRange) {
                result.add("Amount: " + String.valueOf(e.getAmount()));
                result.add("Type operation: " + String.valueOf(e.getType_operation()));
                result.add("Time operation: " + String.valueOf(e.getTime_operation()));
            }
            return result;
        }
    }

    @Transactional
    public String transferMoney(Long sender_id, Long recipient_id, BigDecimal sum) {
        Client sender = clientRepository.findById(sender_id).orElseThrow(() -> new  ClientNotFoundException(sender_id));
        Client recipient = clientRepository.findById(recipient_id).orElseThrow(() -> new ClientNotFoundException(recipient_id));
        if(sender.getBalance().compareTo(sum) >= 0) {
            sender.setBalance(sender.getBalance().subtract(sum));
            recipient.setBalance(recipient.getBalance().add(sum));
            clientRepository.save(sender);
            clientRepository.save(recipient);

            //Save information about transfer money in database (table Operation)
            Operation operation_sender = new Operation();
            operation_sender.setClient(sender);
            operation_sender.setType_operation(3);
            operation_sender.setAmount(sum.negate());
            operation_sender.setTime_operation(new Date());
            operationRepository.save(operation_sender);

            Operation operation_rec = new Operation();
            operation_rec.setClient(recipient);
            operation_rec.setType_operation(4);
            operation_rec.setAmount(sum);
            operation_rec.setTime_operation(new Date());
            operationRepository.save(operation_rec);

            return "Success";
        }else {
            return "Error the operation";
        }
    }

}









