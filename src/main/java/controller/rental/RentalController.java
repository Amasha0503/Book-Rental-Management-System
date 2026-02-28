package controller.rental;

import com.jfoenix.controls.JFXTextField;
import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Book;
import model.Rental;
import model.tm.RentalTM;
import service.custom.RentalService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RentalController implements Initializable {

    @FXML
    private JFXTextField txtFines;

    @FXML
    private TableColumn columnBookID;

    @FXML
    private TableColumn columnCustomerID;

    @FXML
    private TableColumn columnDueDate;

    @FXML
    private TableColumn columnFines;

    @FXML
    private TableColumn columnIssueDate;

    @FXML
    private TableColumn columnRentalID;

    @FXML
    private TableColumn columnReturnDate;

    @FXML
    private DatePicker dpDueDate;

    @FXML
    private DatePicker dpIssueDate;

    @FXML
    private DatePicker dpReturnDate;

    @FXML
    private TableView tblRental;

    @FXML
    private JFXTextField txtBookID;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtRentalID;

    @Inject
    RentalService serviceType;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (serviceType.deleteRental(Integer.parseInt(txtRentalID.getText()))) {
            new Alert(Alert.AlertType.INFORMATION, "Rental Deleted!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Rental NOT Deleted!").show();
        }
    }

    @FXML
    void btnIssueOnAction(ActionEvent event) {
        Rental rental = new Rental(
                0,
                Integer.parseInt(txtBookID.getText()),
                Integer.parseInt(txtCustomerID.getText()),
                dpIssueDate.getValue(),
                dpDueDate.getValue(),
                null,
                0.0

        );
        System.out.println(rental);

        try {
            if (serviceType.addRental(rental)) {
                new Alert(Alert.AlertType.INFORMATION, "Book Issued !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Book not Issued !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        loadTable();
    }

    public void loadTable() {
        try {
            List<Rental> all = serviceType.getAll();
            ArrayList<RentalTM> rentalTMArrayList = new ArrayList<>();

            all.forEach(rental -> rentalTMArrayList.add(new RentalTM(
                    rental.getRentalId(),
                    rental.getBookId(),
                    rental.getCustomerId(),
                    rental.getIssueDate(),
                    rental.getDueDate(),
                    rental.getReturnDate(),
                    rental.getFines()
            )));

            tblRental.setItems(FXCollections.observableArrayList(rentalTMArrayList));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnReturnOnAction(ActionEvent event) {
        Rental rental = new Rental(
                Integer.parseInt(txtRentalID.getText()),
                Integer.parseInt(txtBookID.getText()),
                Integer.parseInt(txtCustomerID.getText()),
                dpIssueDate.getValue(),
                dpDueDate.getValue(),
                dpReturnDate.getValue(),
                Double.parseDouble(txtFines.getText())
        );
        System.out.println(rental);

        try {
            if (serviceType.updateRental(rental)) {
                new Alert(Alert.AlertType.INFORMATION, "Book Returned !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Book not Returned !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            setTextToValues(serviceType.searchRentalById(Integer.parseInt(txtRentalID.getText())));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setTextToValues(Rental rental) {
        txtRentalID.setText(String.valueOf(rental.getRentalId()));
        txtBookID.setText(String.valueOf(rental.getBookId()));
        txtCustomerID.setText(String.valueOf(rental.getCustomerId()));
        dpIssueDate.setValue(rental.getIssueDate());
        dpDueDate.setValue(rental.getDueDate());
        dpReturnDate.setValue(rental.getReturnDate());
        txtFines.setText(String.valueOf(rental.getFines()));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Rental rental = new Rental(
                Integer.parseInt(txtRentalID.getText()),
                Integer.parseInt(txtBookID.getText()),
                Integer.parseInt(txtCustomerID.getText()),
                dpIssueDate.getValue(),
                dpDueDate.getValue(),
                dpReturnDate.getValue(),
                Double.parseDouble(txtFines.getText())
        );
        System.out.println(rental);

        try {
            if (serviceType.updateRental(rental)) {
                new Alert(Alert.AlertType.INFORMATION, "Rental Updated !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Rental not Updated !").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnRentalID.setCellValueFactory(new PropertyValueFactory<>("rentalId"));
        columnBookID.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        columnCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        columnIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        columnDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        columnReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        columnFines.setCellValueFactory(new PropertyValueFactory<>("fines"));

        loadTable();

        tblRental.getSelectionModel().selectedItemProperty().addListener((observableValue, o, t1) -> {

            assert t1 != null;

            RentalTM rentalTM = (RentalTM) t1;

            Rental rental = new Rental(
                    rentalTM.getRentalId(),
                    rentalTM.getBookId(),
                    rentalTM.getCustomerId(),
                    rentalTM.getIssueDate(),
                    rentalTM.getDueDate(),
                    rentalTM.getReturnDate(),
                    rentalTM.getFines()
            );

            setTextToValues(rental);
        });
    }


}

