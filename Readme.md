# Bank REST API

## About

This is REST API for banks that enables account holders to interact with their bank accounts and transactions

## Database
The default database for testing is Postgres

![database structure](https://github.com/unaPalabra/bank_rest_api/blob/master/bd1.PNG)



## Current functionality

```http
GET /clients/{id} 
```
 returns the client by client id
  
```http
PUT /putMoney/{clientId}/{sum} 
```
 replenishment of the client's client for a specified amount
  
```http
PUT /takeMoney/{clientId}/{sum}
```
 withdrawal of a specified amount from the client's client

