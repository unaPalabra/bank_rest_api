package org.example.exception;

public class ClientNotFoundException extends NullPointerException {
    private   Long id;
    public ClientNotFoundException(Long id){
        this.id = id;
    }

    public ClientNotFoundException() {

    }

    @Override
    public String getMessage() {
        return "Client with id = " + id + " not found";
    }
}
