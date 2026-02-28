package service.custom;

import model.Customer;
import model.Rental;

import java.sql.SQLException;
import java.util.List;

public interface RentalService {
    boolean addRental(Rental rental) throws SQLException;
    boolean updateRental(Rental rental);
    boolean deleteRental(int id);
    Customer searchRentalById(int id) throws SQLException;
    List<Rental> getAll() throws SQLException;
    List<Integer> getAllRentalIDs() throws SQLException;
}
