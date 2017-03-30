package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Show;
import view.Alertboxes;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class MainController implements IController {
    private static MainController instance = new MainController();
    private Scene scene;
    private HashMap<Integer, Show> shows = new HashMap<Integer, Show>(); // K: show id, V: Show object
    private VBox showList = null;
    private final static String SHOWLIST_ID = "#showListScrollPane";

    public static MainController get() {
        return instance;
    }

    public Scene getScene() {
        return scene;
    }

    public void addShow(Show show) {
        shows.put(show.getShowId(), show);
        drawShowListView();
    }

    public void addShows(Collection<Show> shows) {
        Iterator<Show> it = shows.iterator();

        while (it.hasNext()) {
            Show show = it.next();
            this.shows.put(show.getShowId(), show);
        }

        drawShowListView();
    }

    public void removeShow(int showId) {
        shows.remove(showId);
        drawShowListView();
    }

    public Show getShow(int showId) {
        return shows.get(showId);
    }

    // Creates view of show posters
    private void drawShowListView() {
        System.out.println("DEBUG: drawShowListView() CALLED");
        showList.getChildren().clear(); // Reset list

        Iterator it = getShows().iterator();

        int counter = 0;

        while (it.hasNext()) {
            Show show = (Show) it.next();
            counter++;

            HBox showLayout = new HBox();
            if (counter != 1) {
                showLayout.setStyle("-fx-padding: 30 0 0 0");
            } else {
                showLayout.setStyle("-fx-padding: 13 0 0 0");
            }

            // Show poster
            Pane imagePane = new Pane();
            imagePane.setStyle("-fx-padding: 0 15 0 0");

            Image posterImage;
            try {
                posterImage = new Image(show.getImage());
            } catch (IllegalArgumentException ex) {
                posterImage = new Image("/imgs/logan.jpg");
            }
            ImageView imageView = new ImageView();
            imageView.setFitWidth(80);
            imageView.setFitHeight(100);
            imageView.setImage(posterImage);
            imagePane.getChildren().add(imageView);
            imageView.setPreserveRatio(true);

            // Description textarea
            TextArea description = new TextArea();
            description.setEditable(false);
            description.setWrapText(true);
            description.setStyle("-fx-max-height: 100px; -fx-min-width: 100px;");

            // DEBUG STUFF
            description.setText(String.format("" +
                    "Titel: %s\n" +
                    "Spille tid: %d\n" +
                    "ID: %d", show.getTitle(), show.getRunTime(), show.getShowId())
            );


            // Buttons
            HBox buttonsBox = new HBox();
            buttonsBox.setStyle("-fx-spacing: 10px; -fx-padding: 70 0 0 15");

            Button edit = new Button();
            edit.setText("Rediger");

            Button delete = new Button();
            delete.setText("Slet");

            delete.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Alertboxes.showRemoveShowAlertShow(show);
                }
            });

            edit.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                        IController ic = new ShowController(ShowController.Action.EDIT, show);
                        Main.ps.setScene(ic.getScene());
                        //GUIController.editShowWindow(event);

                }
            });


            Button book = new Button("Book");

            buttonsBox.getChildren().addAll(edit, delete, book);

            // All everything to children.. "children"
            showLayout.getChildren().addAll(imagePane, description, buttonsBox);
            showList.getChildren().add(showLayout);

            book.setOnAction(e -> {
                GUIController.goToBooking(e);
        });
        }
    }

    public Collection<Show> getShows() {
        return shows.values();
    }

    private MainController() {
        Parent root = null;

        Main.ps.addEventHandler(javafx.stage.WindowEvent.WINDOW_SHOWING, new EventHandler<javafx.stage.WindowEvent>() {
            @Override
            public void handle(javafx.stage.WindowEvent event) {
                DatabaseController.getInstance().
                        startBackgroundTask(DatabaseController.Task.UPDATE_INITIAL_SHOW_VIEW);
            }
        });

        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        scene = new Scene(root, 1000, 600);
        ScrollPane showListScrollPane = (ScrollPane) scene.lookup(SHOWLIST_ID);
        showListScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        showListScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        showList = (VBox) showListScrollPane.getContent();
    }
}
