package view;

import controller.DatabaseController;
import controller.GUIController;
import controller.ShowController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Show;

import java.io.IOException;

public class Alertboxes {

    public static void showAddShowAlertShow(ShowController sc) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på at tilføje?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

            if (!sc.validateAddShowFields()) {
                return;
            } else {
                alert.close();
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Nu er forestillingen tilføjet", ButtonType.OK);
                alert2.showAndWait();

                if (alert2.getResult() == ButtonType.OK) {
                    controller.GUIController.mainWindow();
                }

              }
        } else if (alert.getResult() == ButtonType.NO) {
            alert.close();
        }
    }

    public static void showErrorAlertBox() throws IOException {

        Alert alert = new Alert(Alert.AlertType.ERROR, "Der skete en fejl!\n" +
                "Tjek venligst de indtastede informationer og prøv igen", ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            alert.close();
        }
    }

    public static void showRemoveShowAlertShow(Show show) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på at du vil slette denne forestilling?", ButtonType.YES, ButtonType.NO);

        alert.showAndWait();

        switch (alert.getResult().getButtonData()) {
            case YES:
                DatabaseController.getInstance().startBackgroundTask(DatabaseController.Task.REMOVE_SHOW_FROM_VIEW, show);
                alert.close();
                break;
            case NO:
                alert.close();
                break;
        }
    }

    public static void editShowAlertShow(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på at ændre denne forestilling?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            alert.close();

            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Nu er forestillingen ændret!", ButtonType.OK);
            alert2.showAndWait();

            if (alert2.getResult() == ButtonType.OK) {
                GUIController.mainWindow();
            }
        } else if (alert.getResult() == ButtonType.NO) {
            alert.close();
        }
    }

    public static void bookTicketAlertbox(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på at booke denne billet?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            alert.close();

            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Booking er nu gennemført!", ButtonType.OK);
            alert2.showAndWait();

            if (alert2.getResult() == ButtonType.OK) {
                GUIController.mainWindow();
            }
        } else if (alert.getResult() == ButtonType.NO) {
            alert.close();
        }
    }

}
