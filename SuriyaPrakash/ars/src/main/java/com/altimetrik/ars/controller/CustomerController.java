package com.altimetrik.ars.controller;

import com.altimetrik.ars.model.Customer;
import com.altimetrik.ars.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/ars/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> customerRegistration(@Valid @RequestBody Customer customer) {
        Customer customer1 = customerService.saveCustomerDetails(customer);
        return new ResponseEntity<>(customer1, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        String result = customerService.login(email, password);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomerDetails(
            @RequestParam Long customerId,
            @RequestBody Customer updatedCustomer) {
            Customer updated = customerService.updateCustomerDetails(customerId, updatedCustomer);
            return new ResponseEntity<>(updated, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestParam Long customerId) {
            customerService.deleteCustomer(customerId);
            return ResponseEntity.ok("Customer with ID " + customerId + " deleted successfully");
    }
}
