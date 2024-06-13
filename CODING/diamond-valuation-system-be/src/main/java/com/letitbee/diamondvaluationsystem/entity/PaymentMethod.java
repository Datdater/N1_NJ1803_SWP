package com.letitbee.diamondvaluationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class PaymentMethod {

    @Id
    @GeneratedValue(
            strategy = jakarta.persistence.GenerationType.IDENTITY
    )
    private Long id;
    @Column(columnDefinition = "varchar(50)",nullable = false)
    private String paymentModeName;
    @OneToMany(mappedBy = "paymentMethod",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Payment> payment = new HashSet<>();
}
