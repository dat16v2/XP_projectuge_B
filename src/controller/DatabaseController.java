package controller;

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
                    HashSet<Show> shows = new HashSet<Show>();
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

                    shows.add(demoShow2);
                    shows.add((demoShow));
                    shows.add((demoShow3));
                    shows.add((demoShow4));
                    shows.add((demoShow5));

                    MainController.get().addShows(shows);
                    break;
            }
        }
    }

    public enum Task {
        UPDATE_SHOW_VIEW
    }
}