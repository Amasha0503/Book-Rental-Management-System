package repository.custom.impl;

import model.Customer;
import model.Rental;
import repository.custom.RentalRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalRepositoryImpl implements RentalRepository {
    @Override
    public boolean create(Rental rental) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Rental rental) {
        return false;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }

    @Override
    public Rental getById(Integer integer) throws SQLException {
        return null;
    }


    @Override
    public List<Rental> getAll() throws SQLException {
        try {

            ResultSet resultSet = CrudUtil.execute("SELECT * FROM rental");

            ArrayList<Rental> rentalArrayList = new ArrayList<>();

            while (resultSet.next()){
                rentalArrayList.add(
                        new Rental(
                                resultSet.getInt(1),
                                resultSet.getInt(2),
                                resultSet.getInt(3),
                                resultSet.getDate(4).toLocalDate(),
                                resultSet.getDate(5).toLocalDate(),
                                resultSet.getDate(6).toLocalDate(),
                                resultSet.getDouble(7)
                        )
                );
            }

            return rentalArrayList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
