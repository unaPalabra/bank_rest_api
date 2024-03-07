package org.example.model;

import org.springframework.data.annotation.Id;
import java.math.BigDecimal;

public class Client {

    @Id
    private Long client_id;
    private BigDecimal current_balance;


    public Long getClient_id() {
        return client_id;
    }
    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

   public BigDecimal getCurrent_balance() {
        return current_balance;
    }
    public void setCurrent_balance(BigDecimal current_balance) {
        this.current_balance = current_balance;
    }

    @Override
    public String toString() {
        return "Client{" +
                ", client_id=" + client_id +
                ", current_balance=" + current_balance +
                '}';
    }

}
