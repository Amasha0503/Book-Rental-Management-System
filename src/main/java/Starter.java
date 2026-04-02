import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppModule;
import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {
    private static Injector injector;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() {
        injector = Guice.createInjector(new AppModule());

    }

    @Override
    public void start(Stage stage) throws Exception {
       /*FXMLLoader loader = new FXMLLoader(getClass().getResource("view/menuBar.fxml"));

        loader.setControllerFactory(injector::getInstance);

        stage.setScene(new Scene(loader.load()));
        stage.show();
*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/login.fxml"));
        loader.setControllerFactory(injector::getInstance);

        Parent root = loader.load();

        LoginController controller = loader.getController();
        controller.setInjector(injector);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
