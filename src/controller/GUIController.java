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

import javax.xml.soap.Text;
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

    public static void mainWindow() throws IOException {
        Main.ps.setScene(MainController.get().getScene());
    }

    public void showAddShowAlert() throws IOException {
        //view.Alertboxes.showAddShowAlertShow();
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
            mainWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static boolean validateAddShowFields() throws IOException {
        TextField titleLabel = (TextField) CreateShowController.get().getScene().lookup("#titleField");
        String title = titleLabel.getText();

        TextField actorLabel = (TextField) CreateShowController.get().getScene().lookup("#actorField");
        String actor = actorLabel.getText();

        TextField timeLabel = (TextField) CreateShowController.get().getScene().lookup("#runtimeField");
        String time = timeLabel.getText();

        System.out.println(timeLabel.getText());


        if (title.equals("") || actor.equals("") || time.equals(""))
        {
            Alertboxes.showErrorAlertBox();
            return false;
        } else
         //   DatabaseController.getInstance().startBackgroundTask();

        createNewShowFromShowScene(title, actor, time);
        return true;
    }

    public static void createNewShowFromShowScene(String title, String actor, String time) {
        Show show = new Show(title, actor, time);

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
