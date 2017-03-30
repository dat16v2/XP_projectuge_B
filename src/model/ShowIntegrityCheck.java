package model;

import java.util.HashMap;
import java.util.Iterator;

public class ShowIntegrityCheck extends IntegrityCheck {
    private Show show;
    private Show cShow;
    // Nodes
    private IntegrityCheckNode<Show> icnShow;
    private IntegrityCheckNode<Rating> icnRating;
    private HashMap<Integer, IntegrityCheckNode<Actor>> icnActors;
    private HashMap<Integer, IntegrityCheckNode<Genre>> icnGenres;

    public ShowIntegrityCheck() {

    }

    public void run(Show show, Show cShow) {
        this.show = show;
        this.cShow = cShow;

        // Run through Show fields
        icnShow = new IntegrityCheckNode<Show>(show, "show");
        checkShow();

        // Run through Rating fields
        icnRating = new IntegrityCheckNode<>(show.getAgeLimit(), "rating");
        checkRating();

        icnActors = checkMap(cShow.getActorList(), show.getActorList(), "actor");

        icnGenres = checkMap(cShow.getGenreList(), show.getGenreList(), "genre");
    }

    private <ID, TYPE> HashMap<ID, IntegrityCheckNode<TYPE>> checkMap(HashMap<ID, TYPE> c, HashMap<ID, TYPE> o, String eName) {
        HashMap<ID, IntegrityCheckNode<TYPE>> iEL = new HashMap<ID, IntegrityCheckNode<TYPE>>();

        HashMap<ID, TYPE> copy = new HashMap<ID, TYPE>(c);
        Iterator<TYPE> it = c.values().iterator();
        while (it.hasNext()) {
            TYPE e = it.next();
            Object id = ((IntegrityCheckNodeAction)e).getId();

            IntegrityCheckNode<TYPE> icn = new IntegrityCheckNode<TYPE>(e, eName);

            if (!c.containsKey(id)) {
                icn.setNewNode(true);
            }

            iEL.put((ID) id, icn);
            copy.remove((ID) id);
        }

        it = copy.values().iterator();

        while (it.hasNext()) {
            TYPE e = it.next();
            Object id = ((IntegrityCheckNodeAction)e).getId();
            IntegrityCheckNode<TYPE> icn = new IntegrityCheckNode<TYPE>(e, eName);
            icn.setDeleted(true);
            iEL.put((ID) id, icn);
        }

        return iEL;
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

    public IntegrityCheckNode<Show> getIcnShow() {
        return icnShow;
    }

    public IntegrityCheckNode<Rating> getIcnRating() {
        return icnRating;
    }

    public HashMap<Integer, IntegrityCheckNode<Actor>> getIcnActors() {
        return icnActors;
    }

    public HashMap<Integer, IntegrityCheckNode<Genre>> getIcnGenres() {
        return icnGenres;
    }
}