package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.nio.file.Paths;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = Paths.get("./src/main/resources/fxml/chatfx.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.setTitle("Chat FX");
        primaryStage.show();
    }

}
