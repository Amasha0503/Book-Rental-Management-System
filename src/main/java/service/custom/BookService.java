package service.custom;

import model.Book;


import java.sql.SQLException;
import java.util.List;

public interface BookService {
    boolean addBook(Book book) throws SQLException;
    boolean updateBook(Book book) throws SQLException;
    boolean deleteBook(int id);
    Book searchBookById(int id) throws SQLException;
    List<Book> getAll() throws SQLException;
    int getTotalQuantity() throws SQLException;
}
