package com.finance.frauddetection.repository;

import com.finance.frauddetection.model.Customer;
import com.finance.frauddetection.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository implements ICustomerRepository {
//    private List<Customer> customers = new ArrayList<>();

    private JdbcTemplate jdbcTemplate;
    private RowMapper<Customer> rowMapper = (ResultSet rs, int rowNum) -> new Customer(
            rs.getInt("id"),
            rs.getString("account_number"),
            rs.getString("name"),
            rs.getString("registered_country")
    );
    public CustomerRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    @Override
    public List<Customer> getCustomers(){
        return jdbcTemplate.query("SELECT * FROM customer ORDER BY id DESC",rowMapper);
    }

    @Override
    public Customer getCustomerById(int id){
        return jdbcTemplate.queryForObject("SELECT * FROM customer WHERE id = ?",rowMapper,id);
    }

    @Override
    public int saveCustomer(Customer customer){
        KeyHolder keyholder = new GeneratedKeyHolder();
        String sql = "INSERT INTO customer (account_number, name, registered_country) VALUES (?,?,?)";
        jdbcTemplate.update(connection->{
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getAccountNumber());
            ps.setString(3,customer.getRegisteredCountry());
            return ps;
        },keyholder);
        return keyholder.getKey().intValue();
    }
    @Override
    public void updateName(int id, String name){
        jdbcTemplate.update("UPDATE customer SET name = ? WHERE id = ?",name, id);
    };

    @Override
    public void updateAccountNumber(int id, String acn){
        jdbcTemplate.update("UPDATE customer SET account_number = ? WHERE id = ?",acn, id);
    };

    @Override
    public void updateCountry(int id, String country){
        jdbcTemplate.update("UPDATE customer SET registered_country = ? WHERE id = ?",country, id);
    };
}
