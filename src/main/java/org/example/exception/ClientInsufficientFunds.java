package org.example.exception;

public class ClientInsufficientFunds  extends RuntimeException{
    private  final Long client_id;
    public ClientInsufficientFunds(Long client_id){
        this.client_id = client_id;
    }

    @Override
    public String getMessage() {
        return "There are not enough funds on the client's account (id= " + client_id + ")";
    }
}
