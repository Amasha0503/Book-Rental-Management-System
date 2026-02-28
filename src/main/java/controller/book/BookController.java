package controller.book;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.tm.BookTM;
import service.custom.BookService;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;



public class BookController implements Initializable {

    @FXML
    private JFXComboBox cmbCategory;

    @FXML
    private TableColumn columnAuthor;

    @FXML
    private TableColumn columnCategory;

    @FXML
    private TableColumn columnId;

    @FXML
    private TableColumn columnQty;

    @FXML
    private TableColumn columnTitle;

    @FXML
    private TableView tblBook;

    @FXML
    private JFXTextField txtAuthor;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXTextField txtTitle;

    @Inject
    BookService serviceType;

    @FXML
    void btnAddNewOnAction(ActionEvent event) {
        Book book = new Book(
                0,
                txtTitle.getText(),
                txtAuthor.getText(),
                cmbCategory.getValue().toString(),
                Integer.parseInt(txtQty.getText())
        );
        System.out.println(book);

        try {
            if (serviceType.addBook(book)) {
                new Alert(Alert.AlertType.INFORMATION, "Book Added !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Book not Added !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (serviceType.deleteBook(Integer.parseInt(txtId.getText()))) {
            new Alert(Alert.AlertType.INFORMATION, "Book Deleted!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Book NOT Deleted!").show();
        }

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    public void loadTable() {

        try {
            List<Book> all = serviceType.getAll();
            ArrayList<BookTM> bookTMArrayList = new ArrayList<>();

            all.forEach(book -> {
                bookTMArrayList.add(new BookTM(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getCategory(),
                        book.getQuantity()
                ));
            });
            tblBook.setItems(FXCollections.observableArrayList(bookTMArrayList));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            setTextToValues(serviceType.searchBookById(Integer.parseInt(txtId.getText())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTextToValues(Book book) {
        txtId.setText(String.valueOf(book.getId()));
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        cmbCategory.setValue(book.getCategory());
        txtQty.setText(String.valueOf(book.getQuantity()));
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Book book = new Book(
                Integer.parseInt(txtId.getText()),
                txtTitle.getText(),
                txtAuthor.getText(),
                cmbCategory.getValue().toString(),
                Integer.parseInt(txtQty.getText())
        );
        System.out.println(book);

        try {
            if (serviceType.updateBook(book)) {
                new Alert(Alert.AlertType.INFORMATION, "Book Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Book not Updated !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cmbCategory.setItems(
                FXCollections.observableArrayList(
                        Arrays.asList("Novel",
                                "Fantasy",
                                "Mystery",
                                "Science Fiction",
                                "Biography",
                                "Self Help",
                                "Business",
                                "Information Technology",
                                "Management",
                                "Children",
                                "Romance",
                                "History",
                                "Finance",
                                "Education",
                                "Adventure"
                        ))
        );

        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        columnAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        columnQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadTable();

        tblBook.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {

            assert t1 != null;

            BookTM bookTM = (BookTM) t1;

            Book book = new Book(
                    bookTM.getId(),
                    bookTM.getTitle(),
                    bookTM.getAuthor(),
                    bookTM.getCategory(),
                    bookTM.getQuantity()
            );

            setTextToValues(book);
        });

    }
}

