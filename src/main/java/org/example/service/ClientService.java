package org.example.service;

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
        operation.setAmount(sum);
        operation.setTime_operation(new Date());
        operationRepository.save(operation);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>("Operation status: Success", headers, HttpStatus.OK);
    }

    @Transactional
    public  List<String> getOperationList(long id, Date beginDate, Date endDate) {
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
            List<Operation> listRange =
                    operationRepository.findOperationsByClientIdAndDateRange(client.getId(), beginDate, endDate);
            for (Operation e : listRange) {
                result.add("Amount: " + String.valueOf(e.getAmount()));
                result.add("Type operation: " + String.valueOf(e.getType_operation()));
                result.add("Time operation: " + String.valueOf(e.getTime_operation()));
            }
            return result;
        }
    }

}









