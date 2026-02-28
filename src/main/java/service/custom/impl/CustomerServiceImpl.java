package service.custom.impl;

import com.google.inject.Inject;
import model.Book;
import model.Customer;
import repository.custom.CustomerRepository;
import service.custom.CustomerService;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        return customerRepository.create(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try {
            return customerRepository.update(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteCustomer(int id) {
        return customerRepository.deleteById(String.valueOf(id));
    }

    @Override
    public Customer searchCustomerById(int id) throws SQLException {
        return customerRepository.getById(String.valueOf(id));
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return customerRepository.getAll();
    }

    @Override
    public List<Integer> getAllCustomerIDs() throws SQLException {
        return List.of();
    }

    @Override
    public int getTotalCustomers() throws SQLException {
        List<Customer> allCustomers = customerRepository.getAll();
        return allCustomers.size();
    }
}
