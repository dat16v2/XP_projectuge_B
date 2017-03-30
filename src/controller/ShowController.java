package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Show;
import view.Alertboxes;

import java.io.IOException;

public class ShowController implements IController {
    private Scene scene;
    private Show show = null;

    @Override
    public Scene getScene() {
        return scene;
    }

    public enum Action {
        CREATE,
        EDIT
    }

    public ShowController(Action action, Show show) {
        Parent root = null;
        ShowController sc = this;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/addShowScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        scene = new Scene(root, 1000, 600);

        switch (action) {
            case CREATE:
                {
                    Button button = (Button) scene.lookup("#actionButton");
                    button.setText("Tilføj");
                    button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                view.Alertboxes.showAddShowAlertShow(sc);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            case EDIT:
                {
                    this.show = show;
                    Button button = (Button) scene.lookup("#actionButton");
                    button.setText("Gem ændringer");
                    button.setPrefWidth(90);
                    button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Trying to save show changes.");
                        }
                    });
                }
                break;
        }
    }

    public boolean validateAddShowFields() throws IOException {
        TextField titleLabel = (TextField) this.getScene().lookup("#titleField");
        String title = titleLabel.getText();

        TextField actorLabel = (TextField) this.getScene().lookup("#actorField");
        String actor = actorLabel.getText();

        TextField timeLabel = (TextField) this.getScene().lookup("#runtimeField");
        String time = timeLabel.getText();

        System.out.println(timeLabel.getText());


        if (title.equals("") || actor.equals("") || time.equals(""))
        {
            Alertboxes.showErrorAlertBox();
            return false;
        } else
            //   DatabaseController.getInstance().startBackgroundTask();

            GUIController.createNewShowFromShowScene(title, actor, time);
        return true;
    }

}