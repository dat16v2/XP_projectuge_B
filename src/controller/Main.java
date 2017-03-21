package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage ps;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ps = primaryStage;
        ps.setMinWidth(1000);
        ps.setMinHeight(600);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainScene.fxml"));
        primaryStage.setTitle("Super nice program");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}