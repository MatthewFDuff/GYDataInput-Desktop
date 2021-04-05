package gydatainput;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    static MainController controller; // Manages the main functionality of the application.
    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Main.fxml"));
        Parent root = fxmlLoader.load();

        controller = fxmlLoader.getController();
        primaryStage.getIcons().add(new Image(MainController.class.getResourceAsStream("images/PSPPGP.png")));
        primaryStage.setTitle("Growth & Yield Desktop Application");
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
        stage = primaryStage;
    }

    // Allows other controller classes to communicate with the main controller.
    public static MainController getMainController() {
        return controller;
    }

    public static Stage getStage() { return stage; }

    public static void main(String[] args) {
        launch(args);
    }
}
