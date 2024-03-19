package org.example.exception;

public class ClientNotFoundException extends RuntimeException{
    private  final Long client_id;
    public ClientNotFoundException(Long client_id){
        this.client_id = client_id;
    }

    @Override
    public String getMessage() {
        return "Client with id = " + client_id + " not found";
    }
}
