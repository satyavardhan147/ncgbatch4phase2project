package com.flight.booking.requestDTO;

import jakarta.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

public class LoginDTO {
    @Email(message = "Email is not Valid")
    private String email;
    @Length(min = 5,message = "Please Enter password of Minimum 5 length")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
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
}
