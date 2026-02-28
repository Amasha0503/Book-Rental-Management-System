package controller;

import com.google.inject.Inject;
import com.google.inject.Injector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class MenuBarController {

    @Inject
    private Injector injector;

    @FXML
    private AnchorPane dashRoot;

    @FXML
    @SuppressWarnings("unused")
    void btnBookOnAction(ActionEvent event) {
        try {
            URL resource = this.getClass().getResource("/view/book.fxml");
            assert resource != null;

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(injector::getInstance);
            Parent parent = loader.load();

            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    @SuppressWarnings("unused")
    void btnCustomerOnAction(ActionEvent event) {
        try {
            URL resource = this.getClass().getResource("/view/customer.fxml");
            assert resource != null;

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(injector::getInstance);
            Parent parent = loader.load();

            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    @SuppressWarnings("unused")
    void btnDashboardOnAction(ActionEvent event) {
        try {
            URL resource = this.getClass().getResource("/view/dashboard.fxml");
            assert resource != null;

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(injector::getInstance);
            Parent parent = loader.load();

            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    @SuppressWarnings("unused")
    void btnRentalOnAction(ActionEvent event) {
        try {
            URL resource = this.getClass().getResource("/view/rental.fxml");
            assert resource != null;

            FXMLLoader loader = new FXMLLoader(resource);
            loader.setControllerFactory(injector::getInstance);
            Parent parent = loader.load();

            dashRoot.getChildren().clear();
            dashRoot.getChildren().add(parent);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    @SuppressWarnings("unused")
    void btnSignOutOnAction(ActionEvent event) {
        // TODO: Implement sign out functionality
    }

}
