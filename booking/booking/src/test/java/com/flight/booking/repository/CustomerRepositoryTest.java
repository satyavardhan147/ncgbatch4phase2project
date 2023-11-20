package com.flight.booking.repository;



import com.flight.booking.entity.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    Customer customer;

    @BeforeEach
    void setUp() {
        String str = "1999-09-14";
        Date date = Date.valueOf(str);
         customer = new Customer("customer1", date, "customer1@gmail.com", "password123","customer1SSN", "SSNNO1");
         customerRepository.save(customer);
    }

    @AfterEach
    void tearDown() {
         customer = null;
         customerRepository.deleteAll();
    }

    @Test
    void testFindByCustomerNameFound()
    {
        Optional<Customer> customerOptional = customerRepository.findByEmailAndPassword("customer1@gmail.com","password123");
        Customer customerTest = customerOptional.get();
        Assertions.assertThat(customer.getCustomerId()).isEqualTo(customerTest.getCustomerId());
        Assertions.assertThat(customer.getCustomerName()).isEqualTo(customerTest.getCustomerName());
        Assertions.assertThat(customer.getDateOfBirth()).isEqualTo(customerTest.getDateOfBirth());
        Assertions.assertThat(customer.getSsnNumber()).isEqualTo(customerTest.getSsnNumber());
        Assertions.assertThat(customer.getSsnType()).isEqualTo(customerTest.getSsnType());


    }
    @Test
    void testFindByCustomerNameNotFound()
    {
        Optional<Customer> customerOptional = customerRepository.findByEmailAndPassword("customer2@gmail.com","password123456");

        Assertions.assertThat(customerOptional.isEmpty()).isTrue();
    }
}
