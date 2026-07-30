package com.finance.frauddetection.services;

import com.finance.frauddetection.model.Customer;
import com.finance.frauddetection.model.Transaction;
import com.finance.frauddetection.repository.ICustomerRepository;
import com.finance.frauddetection.repository.ITransactionRepository;

import java.util.List;

public class CustomerService {
    private ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.getCustomers();
    }

    public Customer getCustomerById(int id){
        return customerRepository.getCustomerById(id);
    }

    public Customer proccessCustomer(Customer customer){
        int cust = customerRepository.saveCustomer(customer);
        return getCustomerById(cust);
    }

    public void updateCustomerName(int id, String name){
        customerRepository.updateName(id,name);
    }

    public void updateAccountNumber(int id, String accn){
        customerRepository.updateAccountNumber(id,accn);
    }

    public void updateCountry(int id, String country){
        customerRepository.updateCountry(id,country);
    }
}
