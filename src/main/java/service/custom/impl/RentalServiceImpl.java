package service.custom.impl;

import model.Rental;
import repository.RepositoryFactory;
import repository.custom.RentalRepository;
import service.custom.RentalService;
import util.RepositoryType;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class RentalServiceImpl implements RentalService {

    RentalRepository rentalRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.RENTAL);


    @Override
    public boolean addRental(Rental rental) throws SQLException {
        return rentalRepository.create(rental);
    }

    @Override
    public boolean updateRental(Rental rental) throws SQLException {
        return rentalRepository.update(rental);
    }

    @Override
    public boolean deleteRental(int id) {
        return rentalRepository.deleteById(id);
    }

    @Override
    public Rental searchRentalById(int id) throws SQLException {
        return rentalRepository.getById(id);
    }

    @Override
    public List<Rental> getAll() throws SQLException {
        return rentalRepository.getAll();
    }

    @Override
    public double calculateFines(LocalDate dueDate, LocalDate returnDate) {

        long daysDifference = java.time.temporal.ChronoUnit.DAYS.between(dueDate, returnDate);

        if (daysDifference > 0) {
            return daysDifference * 50.0;
        }
        return 0.0;
    }

    @Override
    public double getTotalFines() throws SQLException {
        List<Rental> allRentals = rentalRepository.getAll();
        double totalFines = 0.0;
        for (Rental rental : allRentals) {
            totalFines += rental.getFines();
        }
        return totalFines;
    }

    @Override
    public int getActiveRentalsCount() throws SQLException {
        List<Rental> allRentals = rentalRepository.getAll();
        int activeCount = 0;
        for (Rental rental : allRentals) {
            if (rental.getReturnDate() == null) {
                activeCount++;
            }
        }
        return activeCount;
    }

    @Override
    public int getOverdueRentalsCount() throws SQLException{
        List<Rental> allRentals = rentalRepository.getAll();
        int overdueCount = 0;
        LocalDate today = LocalDate.now();
        for (Rental rental : allRentals) {
            if (rental.getReturnDate() == null && rental.getDueDate().isBefore(today)) {
                overdueCount++;
            }
        }
        return overdueCount;
    }


}
