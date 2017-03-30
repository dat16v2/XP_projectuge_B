package model;

import java.util.HashSet;
import java.util.Iterator;

public class ShowIntegrityCheck extends IntegrityCheck {
    private Show show;
    private Show cShow;
    // Nodes
    private IntegrityCheckNode<Show> icnShow;
    private IntegrityCheckNode<Rating> icnRating;
    private HashSet<IntegrityCheckNode<Actor>> icnActors;

    public ShowIntegrityCheck() {

    }

    void run(Show show, Show cShow) {
        this.show = show;
        this.cShow = cShow;

        // Run through Show fields
        icnShow = new IntegrityCheckNode<Show>(show, "show");
        checkShow();

        // Run through Rating fields
        icnRating = new IntegrityCheckNode<>(show.getAgeLimit(), "rating");
        checkRating();

        // Loop through all Actor objects and run through their fields
        icnActors = new HashSet<IntegrityCheckNode<Actor>>();
        
    }


    private void checkShow() {
        if (!show.getTitle().equals(cShow.getTitle())) {
            icnShow.setAltered(true);
            return;
        }

        if (show.getRunTime() != cShow.getRunTime()) {
            icnShow.setAltered(true);
            return;
        }

        if (!show.getImage().equals(cShow.getImage())) {
            icnShow.setAltered(true);
        }
    }

    private void checkRating() {
        if (show.getAgeLimit().getName().equals(cShow.getAgeLimit().getName())) {
            icnRating.setAltered(false);
        }
    }
}