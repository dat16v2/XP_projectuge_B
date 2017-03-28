package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage ps;
    public static ClassLoader cl;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ps = primaryStage;
        ps.setMinWidth(1000);
        ps.setMinHeight(600);
        cl = getClass().getClassLoader();

        primaryStage.setTitle("KinoXP Administration");
        primaryStage.setScene(MainController.get().getScene());
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}