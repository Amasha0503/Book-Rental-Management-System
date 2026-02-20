package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CustomerController {

    @FXML
    private JFXComboBox cmbGender;

    @FXML
    private TableColumn columnAddress;

    @FXML
    private TableColumn columnContactNo;

    @FXML
    private TableColumn columnDOB;

    @FXML
    private TableColumn columnGender;

    @FXML
    private TableColumn columnId;

    @FXML
    private TableColumn columnName;

    @FXML
    private DatePicker dpDOB;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    void btnAddNewOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnReloadOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}

