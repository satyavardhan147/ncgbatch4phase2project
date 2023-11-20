package com.example.fms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerMaster {
    @Id
    @Column(name = "c_id", length = 50)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "customerIdSequence",allocationSize = 100, initialValue =50000 ,sequenceName = "customerIdSequence")
    private int c_id;
    @Column(name = "customer_name", length = 100, nullable = false)
    private String customer_name;
    @Column(name = "dob", nullable = false)
    private Date dob;
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "ssn_type", nullable = false)
    private String ssn_type;
    @Column(name = "ssn_no", nullable = false)
    private int ssn_no;

    @OneToOne
    @JoinColumn(name = "login_id")
    private LoginMaster login;
}
