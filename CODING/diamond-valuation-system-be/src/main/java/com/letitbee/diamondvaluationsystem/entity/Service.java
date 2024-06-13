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
public class Service {
    @Id
    @GeneratedValue(
            strategy = jakarta.persistence.GenerationType.IDENTITY
    )
    private Long id;
    @Column(columnDefinition = "nvarchar(50)",nullable = false)
    private String serviceName;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "int", nullable = false)
    private int period;
    @OneToMany(
            mappedBy = "service",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ServicePriceList> servicePriceLists = new HashSet<>();

    @OneToMany(
            mappedBy = "service",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ValuationRequest> valuationRequests = new HashSet<>();

}
