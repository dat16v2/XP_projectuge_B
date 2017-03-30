package controller;

import Database.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Actor;
import model.Genre;
import model.Rating;
import model.Show;

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
            HashSet<Actor> actors;
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
                                view.Alertboxes.showAddShowAlertShow(event);
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


                }
                break;
            case EDIT:
                {
                    this.show = showP;
                    Button button = (Button) scene.lookup("#actionButton");
                    button.setText("Gem ændringer");
                    button.setPrefWidth(90);
                    button.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("Trying to save show changes.");
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
                    ChoiceBox showGenreField = (ChoiceBox) getScene().lookup("#showGenreField");
                    ObservableList<String> genreObservableList = FXCollections.observableArrayList(genresMap.values());
                    showGenreField.setItems(genreObservableList);
                    showGenreField.setValue((String)genresMap.values().toArray()[0]);
                }
                break;
        }
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