package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class MenuBarController {

    @FXML
    private AnchorPane dashRoot;

    @FXML
    void btnBookOnAction(ActionEvent event) {

        try {
            URL resource = this.getClass().getResource("/view/book.fxml");

            assert resource != null;
            Parent parent = FXMLLoader.load(resource);

            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) {

        try {
            URL resource = this.getClass().getResource("/view/customer.fxml");

            assert resource != null;
            Parent parent = FXMLLoader.load(resource);

            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

        try {
            URL resource = this.getClass().getResource("/view/dashboard.fxml");

            assert resource != null;
            Parent parent = FXMLLoader.load(resource);

            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRentalOnAction(ActionEvent event) {

        try {
            URL resource = this.getClass().getResource("/view/rental.fxml");

            assert resource != null;
            Parent parent = FXMLLoader.load(resource);

            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSignOutOnAction(ActionEvent event) {


    }

}

