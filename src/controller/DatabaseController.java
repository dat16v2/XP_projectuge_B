package controller;

import Database.DatabaseConnection;
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

    public void startBackgroundTask(Task task) {
        BackgroundThread taskThread = new BackgroundThread(task);
        Platform.runLater(taskThread);
    }

    public class BackgroundThread extends Thread {
        private Task task;

        public BackgroundThread(Task task) {
            this.task = task;
        }

        @Override
        public void run() {
            runInJavaFXThread();
        }

        private void runInJavaFXThread() {
            switch (task) {
                case UPDATE_SHOW_VIEW:
                    HashSet<Show> shows = (HashSet) DatabaseConnection.getInstance().getShows();

                    MainController.get().addShows(shows);
                    break;
            }
        }
    }

    public enum Task {
        UPDATE_SHOW_VIEW
    }
}