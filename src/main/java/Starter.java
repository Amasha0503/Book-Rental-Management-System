import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppModule;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/menuBar.fxml"));

        loader.setControllerFactory(injector::getInstance);

        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
