package com.finance.frauddetection.repository;

import com.finance.frauddetection.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    List<Customer> getCustomers();

    Customer getCustomerById(int id);

    int saveCustomer(Customer customer);

    void updateName(int id, String name);

    void updateAccountNumber(int id, String acn);

    void updateCountry(int id, String country);
}
