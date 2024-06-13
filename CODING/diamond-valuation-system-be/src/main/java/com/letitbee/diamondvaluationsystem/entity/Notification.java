package com.letitbee.diamondvaluationsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "datetime", nullable = false)
    private Date creationDate;

    @Column(columnDefinition = "varchar(1000)", nullable = false)
    private String message;

    @Column(columnDefinition = "bit default 0")
    private Boolean isRead;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
