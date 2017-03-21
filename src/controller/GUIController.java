package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class GUIController {

    public static void addShowWindow(ActionEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(Main.cl.getResource("view/addShowScene.fxml"));
        Main.ps.setScene(new Scene(newScene, 650, 400));
    }

    public static void mainWindow(ActionEvent actionEvent) throws IOException {
        Main.ps.setScene(MainController.get().getScene());
    }

    public void showAddShowAlert(ActionEvent actionEvent) throws IOException {
        view.Alertboxes.showAddShowAlertShow(actionEvent);
    }

    public void addShowWindowFX(ActionEvent actionEvent) {
        try {
            addShowWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mainWindowFX(ActionEvent actionEvent) {
        try {
            mainWindow(actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
