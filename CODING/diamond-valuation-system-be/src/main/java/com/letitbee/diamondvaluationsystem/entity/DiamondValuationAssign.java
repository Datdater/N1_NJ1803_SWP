package com.letitbee.diamondvaluationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table

public class DiamondValuationAssign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "valuation_request_detail_id", nullable = false)
    private ValuationRequestDetail valuationRequestDetail;

    @Column(columnDefinition = "datetime")
    private Date creationDate;

    @Column(columnDefinition = "text")
    private String comment;

    @Column(columnDefinition = "text")
    private String commentDetail;


    @Column(columnDefinition = "money")
    private Double valuationPrice;

    @OneToOne(mappedBy = "diamondValuationAssign")
    private ValuationRequestDetail valuationRequestDetailId;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "valuation_staff_id", nullable = false)
    private Staff staff;
}
