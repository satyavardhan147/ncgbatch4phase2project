package com.example.fms.service;

import com.example.fms.entity.CustomerMaster;

public interface ICustomerMasterService {
    public boolean registerCustomer(CustomerMaster customer);

    boolean performLogin(String email, String password);

    boolean saveCustomer(CustomerMaster customer);
}