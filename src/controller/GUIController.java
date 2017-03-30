package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import model.Show;
import view.Alertboxes;
import java.io.File;
import java.io.IOException;

public class GUIController {

    public static void addShowWindow(ActionEvent actionEvent) throws IOException {
        IController controller = new ShowController(ShowController.Action.CREATE, null);
        Main.ps.setScene(controller.getScene());
    }

    public static void switchScene(IController controller) {
        Main.ps.setScene(controller.getScene());
    }

    public static void mainWindow(ActionEvent actionEvent) throws IOException {
        Main.ps.setScene(MainController.get().getScene());
    }

    public void showAddShowAlert(ActionEvent actionEvent) throws IOException {
        view.Alertboxes.showAddShowAlertShow(actionEvent);
    }

    public static void editShowWindow(MouseEvent actionEvent) throws IOException {
        Parent newScene = FXMLLoader.load(Main.cl.getResource("view/editShowScene.fxml"));
        Main.ps.setScene(new Scene(newScene, 650, 400));
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


    public static boolean validateAddShowFields() throws IOException {
        AnchorPane ap = (AnchorPane) CreateShowController.get().getScene().lookup("#weirdAnchorPane");
        TextField titleLabelField = (TextField) CreateShowController.get().getScene().lookup("#titleLabel");
        TextField actorLabelField = (TextField) CreateShowController.get().getScene().lookup("#actorLabel");
        TextField timeLabelField = (TextField) CreateShowController.get().getScene().lookup("#timeLabel");
        if (titleLabelField.getText().equals("") || actorLabelField.getText().equals("") || timeLabelField.getText().equals(""))
        {
            Alertboxes.showErrorAlertBox();
            return false;
        } else
            return true;
    }


    public static void goToBooking(ActionEvent actionEvent) {
        Parent newScene = null;
        try {
            newScene = FXMLLoader.load(Main.cl.getResource("view/bookTicket.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.ps.setScene(new Scene(newScene, 1000, 600));
    }

    public void showEditShowAlertShow(ActionEvent actionEvent) throws IOException {
        Alertboxes.editShowAlertShow(actionEvent);
    }

    public void showBookTicketAlertbox(ActionEvent actionEvent) throws IOException {
        Alertboxes.bookTicketAlertbox(actionEvent);
    }

    public void choosePictureFromFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);
        System.out.println("File selected: " + selectedFile.getName());
    }
}
