package service.custom;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
        boolean addCustomer(Customer customer) throws SQLException;
        boolean updateCustomer(Customer customer);
        boolean deleteCustomer(int id);
        Customer searchCustomerById(int id) throws SQLException;
        List<Customer> getAll() throws SQLException;
        List<Integer> getAllCustomerIDs() throws SQLException;
        int getTotalCustomers() throws SQLException;
}
