package com.example.fms.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.fms.controller.CustomerMasterController;
import com.example.fms.entity.CustomerMaster;
import com.example.fms.service.ICustomerMasterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private CustomerMasterController customerController;

    @Mock
    private ICustomerMasterService customerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void testRegisterCustomer_Success() throws Exception {
        CustomerMaster customer = new CustomerMaster();
        customer.setCustomer_name("Anish H");
        customer.setEmail("anish@test.com");
        customer.setPassword("password");

        when(customerService.registerCustomer(customer)).thenReturn(true);

        mockMvc.perform(post("/customer_api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(content().string("Registration Successful"));
    }

    @Test
    void testRegisterCustomer_Failure() throws Exception {
        CustomerMaster customer = new CustomerMaster();
        customer.setCustomer_name("Anish h");
        customer.setEmail("anish@test.com");
        customer.setPassword("password");

        when(customerService.registerCustomer(customer)).thenReturn(false);

        mockMvc.perform(post("/customer_api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Registration failed"));
    }

    private String asJsonString(final Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }
}
