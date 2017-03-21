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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainScene.fxml"));
        //VBox showList = (VBox) root.lookup("showList");

        primaryStage.setTitle("KinoXP Administration");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}