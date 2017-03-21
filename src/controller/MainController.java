package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class MainController {
    private static MainController instance = new MainController();
    private Scene scene;

    public static MainController get() {
        return instance;
    }

    public Scene getScene() {
        return scene;
    }

    private MainController() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        scene = new Scene(root, 1000, 600);
    }
}
