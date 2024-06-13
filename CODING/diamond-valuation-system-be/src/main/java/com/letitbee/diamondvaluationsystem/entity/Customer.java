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
public class Customer {

    @Id
    @GeneratedValue(
            strategy = jakarta.persistence.GenerationType.IDENTITY
    )
    private Long id;
    @Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String firstName;
    @Column(columnDefinition = "nvarchar(50)", nullable = false)
    private String lastName;
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String email;
    @Column(columnDefinition = "char(20)", nullable = false)
    private String phone;
    @Column(columnDefinition = "nvarchar(500)")
    private String address;
    @Column(columnDefinition = "varchar(1000)")
    private String avatar;
    @Column(columnDefinition = "char(50)", nullable = false)
    private String identityDocument;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ValuationRequest> valuationRequestSet = new HashSet<>();
}
