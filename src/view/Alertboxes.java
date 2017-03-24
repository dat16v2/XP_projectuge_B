package view;

import controller.DatabaseController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.Show;

import java.io.IOException;

public class Alertboxes {

    public static void showAddShowAlertShow(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på at tilføje" + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            alert.close();
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Nu er forestillingen tilføjet", ButtonType.OK);
            //alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert2.show();

            // Doesn't work as intended.
            if (alert2.getResult() == ButtonType.OK) {
                controller.GUIController.mainWindow(actionEvent);

                // Parent newScene = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainScene.fxml"));
                // Main.ps.setScene(new Scene(newScene, 1200, 800));

            }
        } else if (alert.getResult() == ButtonType.NO) {
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
}
