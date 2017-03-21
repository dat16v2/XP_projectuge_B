package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
