package controller;

import com.google.inject.Injector;
import jakarta.inject.Inject;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Login;
import service.ServiceFactory;
import service.custom.LoginService;
import util.ServiceType;


import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ComboBox<String> cmbRole;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private TextField txtUserName;

    private final LoginService loginService = ServiceFactory
            .getInstance()
            .getServiceType(ServiceType.LOGIN);

    private Injector injector;

    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    @FXML
    void btnSignInOnAction(ActionEvent event) {
        try {
            // ✅ Validation
            if (txtUserName.getText().isEmpty() ||
                    pwdPassword.getText().isEmpty() ||
                    cmbRole.getValue() == null) {

                showAlert(Alert.AlertType.WARNING, "Validation Error", "Please fill all fields!");
                return;
            }

            // ✅ Create DTO
            Login dto = new Login(
                    txtUserName.getText(),
                    pwdPassword.getText(),
                    cmbRole.getValue()
            );

            // ✅ Call service
            boolean isValid = loginService.login(dto);

            if (isValid) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful!");
                loadMenuBar();
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Email, Password, or Role!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "System Error", "Something went wrong!");
        }
    }

    // ✅ Alert Method
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // ✅ Load Dashboard
    private void loadMenuBar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menuBar.fxml"));

        loader.setControllerFactory(injector::getInstance); // 👈 IMPORTANT

        Parent root = loader.load();

        Stage stage = (Stage) txtUserName.getScene().getWindow();
        stage.setScene(new Scene(root));
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbRole.setItems(FXCollections.observableArrayList(Arrays.asList("admin", "staff")));
    }
}
