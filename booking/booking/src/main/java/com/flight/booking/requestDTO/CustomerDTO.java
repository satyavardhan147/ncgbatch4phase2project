package com.flight.booking.requestDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

public class CustomerDTO
{

    @NotEmpty(message = "customer name is empty")
    private String customerName;

    @NotNull(message = "Date of birth cannot be Empty")
    private Date dateOfBirth;

    @Email(message = "email is not valid")
    private String email;

    @NotEmpty(message = "password cannot be empty")
    @Length(min = 5 , message = "password length should be minimum 5")
    private String password;
    @NotEmpty(message = "SSN Type is Empty")
    @Column(name = "ssn_type")
    private String ssnType;

    @NotEmpty(message = "SSN Number is Empty")
    @Column(name = "ssn_number")
    private String ssnNumber;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerName, Date dateOfBirth, String email, String password, String ssnType, String ssnNumber) {
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.ssnType = ssnType;
        this.ssnNumber = ssnNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsnType() {
        return ssnType;
    }

    public void setSsnType(String ssnType) {
        this.ssnType = ssnType;
    }

    public String getSsnNumber() {
        return ssnNumber;
    }

    public void setSsnNumber(String ssnNumber) {
        this.ssnNumber = ssnNumber;
    }
}
