package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RentalController {

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
    private DatePicker dpDueDtae;

    @FXML
    private DatePicker dpIssueDate;

    @FXML
    private DatePicker dpReturnDate;

    @FXML
    private TableView<?> tblRental;

    @FXML
    private JFXTextField txtBookID;

    @FXML
    private JFXTextField txtCustomerID;

    @FXML
    private JFXTextField txtRentalID;

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnIssueOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnReturnOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}

