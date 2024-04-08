package org.example.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OPERATION")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_operation;

    @Column(name = "type_operation")
    private int type_operation;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "time_operation")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date time_operation;

    @ManyToOne
    private Client client;

}
