package com.finance.frauddetection.services;

import com.finance.frauddetection.model.Customer;
import com.finance.frauddetection.model.Transaction;
import com.finance.frauddetection.repository.ICustomerRepository;
import com.finance.frauddetection.repository.ITransactionRepository;
import com.finance.frauddetection.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FraudDetectionService {
    private ITransactionRepository transactionRepository;
    private ICustomerRepository customerRepository;

    public FraudDetectionService(ITransactionRepository transactionRepository, ICustomerRepository customerRepository){
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(int id){
        return transactionRepository.findById(id);
    }

    public Transaction processTransaction(Transaction transaction){
        int newGeneratedIdTxn = transactionRepository.save(transaction);
        transaction.setId(newGeneratedIdTxn);

        List<String> reasons = new ArrayList<>();

        int riskScore= 0;
        if(transaction.getAmount().compareTo(new BigDecimal(1000000))>0){
            reasons.add("high transaction amount Rs. "+ transaction.getAmount() );
            riskScore +=40;
        }
        int hour = transaction.getTxnTimestamp().getHour();
        if(hour>=0 && hour<=5){
            riskScore +=20;
        }
//        String country = transaction.getTxnCountry();
        Customer customer = customerRepository.getCustomerById(transaction.getCustomerId());
        if(customer != null && customer.getRegisteredCountry().equalsIgnoreCase(transaction.getTxnCountry())){
            reasons.add("customer country mismatched - " + transaction.getTxnCountry());
            riskScore += 30;
        }

        if(!reasons.isEmpty()){
            transactionRepository.updateStatus(transaction.getId(), "FLAGGED");
        }else {
            transactionRepository.updateStatus(transaction.getId(), "SUCCESS");
        }

        return transaction;
    }
}
