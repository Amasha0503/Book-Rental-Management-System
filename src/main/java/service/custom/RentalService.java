package service.custom;

import model.Rental;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface RentalService {
    boolean addRental(Rental rental) throws SQLException;
    boolean updateRental(Rental rental) throws SQLException;
    boolean deleteRental(int id);
    Rental searchRentalById(int id) throws SQLException;
    List<Rental> getAll() throws SQLException;
    double calculateFines(LocalDate dueDate, LocalDate returnDate);
    double getTotalFines() throws SQLException;
}
