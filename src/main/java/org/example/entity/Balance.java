package org.example.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "BALANCE")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long client_id;
    @Column(name = "CURRENT_BALANCE")
    private BigDecimal current_balance;

    protected Balance() {
    }

    public Balance(Long client_id, BigDecimal current_balance) {
        this.client_id = client_id;
        this.current_balance = current_balance;
    }

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

}
