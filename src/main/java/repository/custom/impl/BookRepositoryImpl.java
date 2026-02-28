package repository.custom.impl;

import model.Book;
import repository.custom.BookRepository;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public boolean create(Book book) throws SQLException {
        return CrudUtil.execute("INSERT INTO book VALUES(?,?,?,?,?)",
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getQuantity()
        );
    }

    @Override
    public boolean update(Book book) throws SQLException {
        return CrudUtil.execute("UPDATE book SET title=?, author=?, category=?, quantity=? WHERE id=?",
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getQuantity(),
                book.getId()
        );
    }

    @Override
    public boolean deleteById(Integer integer) {
        try {
            return CrudUtil.execute("DELETE FROM book WHERE id=?", integer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getById(Integer integer) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM book WHERE id=?", integer);
        if (resultSet.next()){
            return new Book(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5)
            );
        }
        return null;
    }


    @Override
    public List<Book> getAll() throws SQLException {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM book");
            ArrayList<Book> bookArrayList = new ArrayList<>();
            while (resultSet.next()){
                bookArrayList.add(
                        new Book(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getInt(5)
                        )
                );
            }
            return bookArrayList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
