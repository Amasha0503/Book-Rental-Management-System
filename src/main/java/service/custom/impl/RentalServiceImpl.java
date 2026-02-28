package service.custom.impl;

import model.Rental;
import repository.RepositoryFactory;
import repository.custom.RentalRepository;
import service.custom.RentalService;
import util.RepositoryType;

import java.sql.SQLException;
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


}
