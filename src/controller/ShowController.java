package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import model.Actor;
import model.Show;

import java.io.IOException;

public class ShowController implements IController {
    private Scene scene;
    private Show showField = null;

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
                                view.Alertboxes.showAddShowAlertShow(event);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                break;
            case EDIT:
                {
                    this.showField = show;
                    Button button = (Button) scene.lookup("#actionButton");
                    button.setText("Gem ændringer");
                    button.setPrefWidth(90);
                    button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Trying to save show changes.");
                            Show[] shows = new Show[2];
                            shows[0] = showField;
                            shows[1] = new Show(showField);
                            shows[1].setTitle("Yee hawh..");
                            shows[1].getAgeLimit().setName("Nihao");
                            shows[1].getActorList().remove(2);
                            Actor actor = new Actor();
                            actor.setId(4);
                            actor.setFirstName("Jakob");
                            shows[1].getActorList().put(actor.getId(), actor);
                            shows[1].getGenreList().remove(7);
                            DatabaseController.getInstance().startBackgroundTask(DatabaseController.Task.UPDATE_SHOW, shows);
                        }
                    });
                }
                break;
        }
    }
}