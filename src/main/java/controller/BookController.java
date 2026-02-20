package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookController {

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

