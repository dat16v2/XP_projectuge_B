package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class GUIController {

        public void switchScene(ActionEvent actionEvent) throws IOException {
            Parent newScene = FXMLLoader.load(getClass().getClassLoader().getResource("view/addShowScene.fxml"));
            Main.ps.setScene(new Scene(newScene, 650, 400));
        }

        public void goBackToMain(ActionEvent actionEvent) throws IOException {
            Parent newScene = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainScene.fxml"));
            Main.ps.setScene(new Scene(newScene, 1200, 800));
        }
}
