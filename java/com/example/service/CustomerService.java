package com.example.service;

import com.example.model.Customer;

import com.example.model.CustomerCard;
import com.example.model.CustomerCard;

import java.util.List;

public interface CustomerService {
    public Customer createCustomer(Customer customer);
    public List<Customer> getCustomers();
    public CustomerCard getCustomerCardById(long customerId);
    public boolean deleteCustomerById(long custId);
}
