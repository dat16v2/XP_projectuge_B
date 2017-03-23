package view;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.IOException;

public class Alertboxes {

    public static void showAddShowAlertShow(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Er du sikker på at tilføje" + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            alert.close();
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Nu er filmen tilføjet", ButtonType.OK);
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
}