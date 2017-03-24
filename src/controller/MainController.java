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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Show;

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
            System.out.printf("%d, %s, %d\n", show.getShowId(), show.getTitle(), counter);

            HBox showLayout = new HBox();
            if (counter != 1) {
                showLayout.setStyle("-fx-padding: 30 0 0 0");
            } else {
                showLayout.setStyle("-fx-padding: 13 0 0 0");
            }

            // Show poster
            Pane imagePane = new Pane();
            imagePane.setStyle("-fx-padding: 0 15 0 0");

            System.out.println(show.getImage());
            Image posterImage = new Image(show.getImage());
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
            edit.setText("Redigere");

            Button delete = new Button();
            delete.setText("Slet");

            buttonsBox.getChildren().addAll(edit, delete);

            // All everything to children.. "children"
            showLayout.getChildren().addAll(imagePane, description, buttonsBox);
            showList.getChildren().add(showLayout);
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
                Show demoShow = new Show();
                demoShow.setShowId(1);
                demoShow.setImage("/imgs/saving-private-ryan.jpg");
                demoShow.setTitle("Saving Private Ryan");
                demoShow.setRunTime(180);

                Show demoShow2 = new Show();
                demoShow2.setShowId(2);
                demoShow2.setImage("/imgs/logan.jpg");
                demoShow2.setTitle("Logan");
                demoShow2.setRunTime(140);

                Show demoShow4 = new Show();
                demoShow4.setShowId(4);
                demoShow4.setImage("/imgs/logan.jpg");
                demoShow4.setTitle("Phuong /r/gonewild");
                demoShow4.setRunTime(169);

                Show demoShow3 = new Show();
                demoShow3.setShowId(3);
                demoShow3.setImage("/imgs/saving-private-ryan.jpg");
                demoShow3.setTitle("Phuong /r/gonewild");
                demoShow3.setRunTime(169);

                Show demoShow5 = new Show();
                demoShow5.setShowId(5);
                demoShow5.setImage("/imgs/saving-private-ryan.jpg");
                demoShow5.setTitle("Phuong /r/gonewild");
                demoShow5.setRunTime(169);

                addShow(demoShow2);
                addShow(demoShow);
                addShow(demoShow3);
                addShow(demoShow4);
                addShow(demoShow5);
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