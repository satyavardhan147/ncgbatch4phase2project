package com.altimetrik.ars.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(generator = "customerId", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "customerId", initialValue = 10101, sequenceName = "customerId")
    private Long customerId;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email address")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @NotNull(message = "Date of birth cannot be null")
    @Past(message = "Date of birth must be in the past")
    private Date dob;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phone;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "^(Male|Female)$", message = "Gender must be Male or Female")
    private String gender;

    @NotBlank(message = "SSN type cannot be blank")
    private String ssnType;

    @NotBlank(message = "SSN number cannot be blank")
    @Pattern(regexp = "\\d{9}", message = "SSN number must be 9 digits")
    private String ssnNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();
}
