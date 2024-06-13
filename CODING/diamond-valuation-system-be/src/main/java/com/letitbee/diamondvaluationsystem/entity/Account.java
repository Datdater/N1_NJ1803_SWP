package com.letitbee.diamondvaluationsystem.entity;


import com.letitbee.diamondvaluationsystem.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity
public class Account  {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false, columnDefinition = "nvarchar(50)",unique = true)
    private String username;
    @Column(nullable = false, columnDefinition = "nvarchar(50)")
    private String password;
    @Column(columnDefinition = "bit default 1")
    private Boolean is_active;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "account")
    private Staff staff;

    @OneToOne(mappedBy = "account")
    private Customer customer;

    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Notification> notifications = new HashSet<>();

}
