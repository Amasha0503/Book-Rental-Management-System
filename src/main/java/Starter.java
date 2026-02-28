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
        // Create Guice injector before starting the application
        injector = Guice.createInjector(new AppModule());
        System.out.println("✓ Guice injector initialized");
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/menuBar.fxml"));
        // Set controller factory to use Guice for dependency injection
        loader.setControllerFactory(injector::getInstance);
        System.out.println("✓ Controller factory set to use Guice");
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
