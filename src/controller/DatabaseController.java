package controller;

import Database.DatabaseConnection;
import com.sun.javaws.exceptions.InvalidArgumentException;
import javafx.application.Platform;
import model.Show;

import java.util.HashSet;

public class DatabaseController {
    private static DatabaseController databaseController = new DatabaseController();

    public static DatabaseController getInstance() {
        return databaseController;
    }

    private DatabaseController() {

    }

    public <T> void startBackgroundTask(Task task, T payload) {
        BackgroundThread<T> taskThread = new BackgroundThread(task, payload);
        Platform.runLater(taskThread);
    }

    public void startBackgroundTask(Task task) {
        startBackgroundTask(task, null);
    }

    public class BackgroundThread<T> extends Thread {
        private Task task;
        private T payload = null;

        public BackgroundThread(Task task) {
            this.task = task;
        }

        public BackgroundThread(Task task, T payload) {
            this.task = task;
            this.payload = payload;
        }

        @Override
        public void run() {
            runInJavaFXThread();
        }

        private void runInJavaFXThread() {
            switch (task) {
                case UPDATE_INITIAL_SHOW_VIEW:
                    HashSet<Show> shows = (HashSet) DatabaseConnection.getInstance().getShows();
                    MainController.get().addShows(shows);
                    break;
                case REMOVE_SHOW_FROM_VIEW:
                    if (!(payload instanceof Show)) {
                        throw new IllegalArgumentException("Show was not provided as parameter to REMOVE_SHOW_FROM_VIEW TASK");
                    }

                    DatabaseConnection.getInstance().deleteShow((Show) payload); // Remove show from DB
                    MainController.get().removeShow(((Show) payload).getShowId()); // Remove show from local cache
                    break;
                case UPDATE_SHOW:
                    if (!(payload instanceof Show)) {
                        throw new IllegalArgumentException("Show was not provided as parameter to REMOVE_SHOW_FROM_VIEW TASK");
                    }

                    DatabaseConnection.getInstance().edit((Show) payload);
                    MainController.get().addShow((Show) payload);
                    break;
            }
        }
    }

    public enum Task {
        UPDATE_INITIAL_SHOW_VIEW,
        REMOVE_SHOW_FROM_VIEW,
        UPDATE_SHOW
    }
}