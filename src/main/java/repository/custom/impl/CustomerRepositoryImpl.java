package repository.custom.impl;

import model.Customer;
import repository.custom.CustomerRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public boolean create(Customer customer) throws SQLException {
        return CrudUtil.execute(
                "INSERT INTO customer (name, contactNo, gender, address, dob) VALUES (?, ?, ?, ?, ?)",
                customer.getName(),
                customer.getContact(),
                customer.getGender(),
                customer.getAddress(),
                customer.getDob()
        );
    }

    @Override
    public boolean update(Customer customer) {
        try {
            return CrudUtil.execute(
                    "UPDATE customer SET name = ?, contactNo = ?, gender = ?, address = ?, dob = ? WHERE id = ?",
                    customer.getName(),
                    customer.getContact(),
                    customer.getGender(),
                    customer.getAddress(),
                    customer.getDob(),
                    customer.getId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteById(String id) {
        try {
            return CrudUtil.execute(
                    "DELETE FROM customer WHERE id = ?",
                    id
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer getById(String id) throws SQLException {
        try {
            ResultSet resultSet = CrudUtil.execute(
                    "SELECT * FROM customer WHERE id = ?",
                    id
            );

            if (resultSet.next()) {
                return new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getDate(6).toLocalDate()
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");

            ArrayList<Customer> customerArrayList = new ArrayList<>();

            while (resultSet.next()){
                customerArrayList.add(
                        new Customer(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getDate(6).toLocalDate()
                        )
                );
            }

            return customerArrayList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
