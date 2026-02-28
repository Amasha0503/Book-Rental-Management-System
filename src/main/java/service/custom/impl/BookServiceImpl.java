package service.custom.impl;

import model.Book;
import repository.RepositoryFactory;
import repository.custom.BookRepository;
import service.custom.BookService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {

    BookRepository bookRepository = RepositoryFactory.getInstance().getRepositoryType(RepositoryType.BOOK);

    @Override
    public boolean addBook(Book book) throws SQLException {
        return bookRepository.create(book);
    }

    @Override
    public boolean updateBook(Book book) throws SQLException {
        return bookRepository.update(book);
    }

    @Override
    public boolean deleteBook(int id) {
        return bookRepository.deleteById(id);
    }

    @Override
    public Book searchBookById(int id) throws SQLException {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getAll() throws SQLException {
        return bookRepository.getAll();
    }

    @Override
    public int getTotalQuantity() throws SQLException {
        List<Book> allBooks = bookRepository.getAll();
        int totalQuantity = 0;
        for (Book book : allBooks) {
            totalQuantity += book.getQuantity();
        }
        return totalQuantity;
    }

}
