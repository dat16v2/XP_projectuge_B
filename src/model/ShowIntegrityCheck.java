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
        icnShow = new IntegrityCheckNode<Show>(cShow, "show");
        checkShow();

        // Run through Rating fields
        icnRating = new IntegrityCheckNode<>(cShow.getAgeLimit(), "rating");
        checkRating();

        icnActors = checkMap(cShow.getActorList(), show.getActorList(), "actor");

        icnGenres = checkMap(cShow.getGenreList(), show.getGenreList(), "genre");
    }

    private <ID, TYPE> HashMap<ID, IntegrityCheckNode<TYPE>> checkMap(HashMap<ID, TYPE> c, HashMap<ID, TYPE> o, String eName) {
        HashMap<ID, IntegrityCheckNode<TYPE>> iEL = new HashMap<ID, IntegrityCheckNode<TYPE>>();

        HashMap<ID, TYPE> copy = new HashMap<ID, TYPE>(c);
        copy.putAll(o);

        Iterator<TYPE> it = c.values().iterator();
        while (it.hasNext()) {
            TYPE e = it.next();
            Object id = ((IntegrityCheckNodeAction)e).getId();

            IntegrityCheckNode<TYPE> icn = new IntegrityCheckNode<TYPE>(e, eName);

            if (!o.containsKey(id)) {
                icn.setNewNode(true);
                System.out.printf("Node %s - ID %d (TO BE ADDED)\n", icn.getElementName(), id);
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
            System.out.printf("Node %s - ID %d (TO BE DELETED)\n", icn.getElementName(), id);
            iEL.put((ID) id, icn);
        }

        return iEL;
    }


    private void checkShow() {
        if (!show.getTitle().equals(cShow.getTitle())) {
            icnShow.setAltered(true);
            System.out.println("Show has been altered");
            return;
        }

        if (show.getRunTime() != cShow.getRunTime()) {
            icnShow.setAltered(true);
            System.out.println("Show has been altered");
            return;
        }

        if (!show.getImage().equals(cShow.getImage())) {
            icnShow.setAltered(true);
            System.out.println("Show has been altered");
        }
    }

    private void checkRating() {
        // System.out.printf("%s(%d), -> %d %s\n", show.getTitle(), show.getShowId(), show.getAgeLimit().getId(), show.getAgeLimit().getName());
        // System.out.printf("%s(%d), -> %d %s\n", cShow.getTitle(), cShow.getShowId(), show.getAgeLimit().getId(), show.getAgeLimit().getName());
        if (show.getAgeLimit().getName().equals(cShow.getAgeLimit().getName())) {
            icnRating.setAltered(false);
        }
        System.out.println("Rating has been altered");
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