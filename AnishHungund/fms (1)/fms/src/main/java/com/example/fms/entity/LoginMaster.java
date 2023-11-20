package com.example.fms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "login")
public class LoginMaster {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "email", length = 45, nullable = false)
    private String email;
    @Column(name = "password", length = 45, nullable = false)
    private String password;
}
