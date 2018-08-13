import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.LogManager;

public class OWApplication extends Application {


    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(
                    OWApplication.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Error: Unable to load logger.properties" + e.toString());
        }
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parent menuPane = null;
        try {
            menuPane = FXMLLoader.load(getClass().getResource("/fxmls/menu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("XML Calculator");

        assert menuPane != null;
        primaryStage.setScene(new Scene(menuPane, 300, 200));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
