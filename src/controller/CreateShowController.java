package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class CreateShowController implements IController {
    private static CreateShowController instance = new CreateShowController();
    private Scene scene;

    public static CreateShowController get() {
        return instance;
    }

    public Scene getScene() {
        return scene;
    }

    private CreateShowController() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/addShowScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        scene = new Scene(root, 650, 400);
    }
}