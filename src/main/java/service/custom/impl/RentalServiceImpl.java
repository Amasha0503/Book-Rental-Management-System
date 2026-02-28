package service.custom.impl;

import model.Customer;
import model.Rental;
import service.custom.RentalService;

import java.sql.SQLException;
import java.util.List;

public class RentalServiceImpl implements RentalService {
    @Override
    public boolean addRental(Rental rental) throws SQLException {
        return false;
    }

    @Override
    public boolean updateRental(Rental rental) {
        return false;
    }

    @Override
    public boolean deleteRental(int id) {
        return false;
    }

    @Override
    public Customer searchRentalById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Rental> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public List<Integer> getAllRentalIDs() throws SQLException {
        return List.of();
    }
}
