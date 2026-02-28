package controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ComboBox cmbRole;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnSignInOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbRole.setItems(
                FXCollections.observableArrayList(
                Arrays.asList("Admin", "Staff"
                )
                ));
    }
}
