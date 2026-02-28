package service.custom;

import model.Rental;

import java.sql.SQLException;
import java.util.List;

public interface RentalService {
    boolean addRental(Rental rental) throws SQLException;
    boolean updateRental(Rental rental) throws SQLException;
    boolean deleteRental(int id);
    Rental searchRentalById(int id) throws SQLException;
    List<Rental> getAll() throws SQLException;
}
