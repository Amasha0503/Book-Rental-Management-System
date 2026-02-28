package repository.custom.impl;

import model.Rental;
import repository.custom.RentalRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalRepositoryImpl implements RentalRepository {
    @Override
    public boolean create(Rental rental) throws SQLException {
        LocalDate returnDate = rental.getReturnDate();
        return CrudUtil.execute("INSERT INTO rental VALUES(?,?,?,?,?,?,?)",
                rental.getRentalId(),
                rental.getBookId(),
                rental.getCustomerId(),
                rental.getIssueDate(),
                rental.getDueDate(),
                returnDate == null ? "0000-00-00" : returnDate,
                rental.getFines()
        );
    }

    @Override
    public boolean update(Rental rental) throws SQLException {
        return CrudUtil.execute("UPDATE rental SET BookID=?,CustomerID=?,IssueDate=?,DueDate=?,ReturnDate=?,Fines=? WHERE RentalID=?",
                rental.getBookId(),
                rental.getCustomerId(),
                rental.getIssueDate(),
                rental.getDueDate(),
                rental.getReturnDate(),
                rental.getFines(),
                rental.getRentalId()

        );
    }

    @Override
    public boolean deleteById(Integer integer) {
        try {
            return CrudUtil.execute("DELETE FROM rental WHERE RentalID=?", integer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Rental getById(Integer integer) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM rental WHERE RentalID=?", integer);
        if (resultSet.next()){
            return new Rental(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getDate(4).toLocalDate(),
                    resultSet.getDate(5).toLocalDate(),
                    resultSet.getDate(6).toLocalDate(),
                    resultSet.getDouble(7)
            );
        }
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
