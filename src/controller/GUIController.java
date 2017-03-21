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
            Main.ps.setScene(new Scene(newScene, 1000, 600));
        }
    public void showAddShowAlert(ActionEvent actionEvent) throws IOException
    {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på at tilføje"  + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            alert.close();
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Nu er filmen tilføjet", ButtonType.OK);
            //alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert2.show();

            // Doesn't work as intended.
            if (alert2.getResult() == ButtonType.OK) {
                goBackToMain(actionEvent);

                // Parent newScene = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainScene.fxml"));
                // Main.ps.setScene(new Scene(newScene, 1200, 800));

            }
        } else if (alert.getResult() == ButtonType.NO) {
            alert.close();
        }
    }
}
