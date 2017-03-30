package controller;

import Database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.Actor;
import model.Genre;
import model.Rating;
import model.Show;
import view.Alertboxes;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class ShowController implements IController {
    private Scene scene;
    private Show show = null;
    private HashMap<Integer, String> ratingsMap;
    private HashMap<Integer, String> genresMap;
    private HashMap<Integer, String> actorsMap;

    @Override
    public Scene getScene() {
        return scene;
    }

    public enum Action {
        CREATE,
        EDIT
    }

    public ShowController(Action action, Show showP) {
        Parent root = null;
        ShowController sc = this;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/addShowScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        scene = new Scene(root, 1000, 600);

        // Get ratings from DB
        {
            HashSet<Rating> ratings = DatabaseConnection.getInstance().getRatings();
            ratingsMap = new HashMap<Integer, String>();

            Iterator<Rating> it = ratings.iterator();

            while (it.hasNext()) {
                Rating rating = it.next();
                ratingsMap.put(rating.getId(), rating.getName());
            }
        }

        // Get genres from DB
        {
            HashSet<Genre> genres = DatabaseConnection.getInstance().getGenres();
            genresMap = new HashMap<Integer, String>();

            Iterator<Genre> it = genres.iterator();

            while (it.hasNext()) {
                Genre genre = it.next();
                genresMap.put(genre.getId(), genre.getName());
            }
        }

        // Get actors from DB
        {
            HashSet<Actor> actors = DatabaseConnection.getInstance().getActors();
            actorsMap = new HashMap<Integer, String>();

            Iterator<Actor> it = actors.iterator();

            while (it.hasNext()) {
                Actor actor = it.next();
                actorsMap.put(actor.getId(), String.format("%s %s", actor.getFirstName(), actor.getLastName()));
            }
        }

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

                    // Add filler data

                    // Rating
                    ChoiceBox showRatingField = (ChoiceBox) getScene().lookup("#showRatingField");
                    ObservableList<String> ratingObservableList = FXCollections.observableArrayList(ratingsMap.values());
                    showRatingField.setItems(ratingObservableList);
                    showRatingField.setValue((String)ratingsMap.values().toArray()[0]);

                    // Genre
                    ScrollPane genreScrollPane = (ScrollPane) getScene().lookup("#showGenreScrollPane");
                    ListView showGenreField = (ListView) genreScrollPane.getContent();

                    ObservableList<String> genreObservableList = FXCollections.observableArrayList(genresMap.values());
                    showGenreField.setItems(genreObservableList);

                    // Actors
                    ScrollPane actorScrollPane = (ScrollPane) getScene().lookup("#showActorScrollPane");
                    ListView actorField = (ListView) actorScrollPane.getContent();
                    ObservableList<String> actorsObservableList = FXCollections.observableArrayList(actorsMap.values());
                    actorField.setItems(actorsObservableList);
                }
                break;
            case EDIT:
                {
                    this.show = showP;
                    Button button = (Button) scene.lookup("#actionButton");
                    button.setText("Gem ændringer");
                    button.setPrefWidth(130);
                    button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Show[] shows = new Show[2]; // 0: Original, 1: Copy
                            shows[0] = show;
                            shows[1] = generateShow();
                            DatabaseController.getInstance().startBackgroundTask(DatabaseController.Task.UPDATE_SHOW, shows);
                        }
                    });

                    // Add existing data to view.

                    // Title
                    TextField titleField = (TextField) getScene().lookup("#titleField");
                    titleField.setText(show.getTitle());

                    // Runtime
                    TextField runtimeField = (TextField) getScene().lookup("#runtimeField");
                    runtimeField.setText(String.format("%d", show.getRunTime()));

                    // Poster path
                    //Button posterPathField = (Button) getScene().lookup("#posterPathField");
                    //posterPathField.setText(show.getImage());

                    // Rating
                    ChoiceBox showRatingField = (ChoiceBox) getScene().lookup("#showRatingField");
                    ObservableList<String> ratingObservableList = FXCollections.observableArrayList(ratingsMap.values());
                    showRatingField.setItems(ratingObservableList);
                    showRatingField.setValue((String)ratingsMap.values().toArray()[0]);

                    // Genre
                    ScrollPane genreScrollPane = (ScrollPane) getScene().lookup("#showGenreScrollPane");
                    ListView showGenreField = (ListView) genreScrollPane.getContent();

                    ObservableList<String> genreObservableList = FXCollections.observableArrayList(genresMap.values());
                    showGenreField.setItems(genreObservableList);

                    // Actors
                    ScrollPane actorScrollPane = (ScrollPane) getScene().lookup("#showActorScrollPane");
                    ListView actorField = (ListView) actorScrollPane.getContent();
                    ObservableList<String> actorsObservableList = FXCollections.observableArrayList(actorsMap.values());
                    actorField.setItems(actorsObservableList);
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

    private Show generateShow() {
        Show show_gen = new Show();

        // Title
        TextField titleField = (TextField) getScene().lookup("#titleField");
        show_gen.setTitle(titleField.getText());

        // Runtime
        TextField runtimeField = (TextField) getScene().lookup("#runtimeField");
        show_gen.setRunTime(Integer.getInteger(runtimeField.getText()));

        // Poster path
        //TextField posterPathField = (TextField) getScene().lookup("#posterPathField");
        show_gen.setImage(show.getImage());

        return show_gen;
    }
}
