package com.example.fms.controller;

import com.example.fms.service.ICustomerMasterService;
import com.example.fms.entity.CustomerMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loginApi")
public class LoginController {
    @Autowired
    private ICustomerMasterService loginService;
    @PostMapping("/auth")
    @ResponseBody
    public ResponseEntity<String> authenticate(@RequestBody CustomerMaster customer) {
        boolean isAuthenticated = loginService.performLogin(customer.getEmail(), customer.getPassword());
        if (isAuthenticated) {
            loginService.saveCustomer(customer);
            return ResponseEntity.ok("LoginMaster successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
