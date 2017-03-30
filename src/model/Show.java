package model;

import java.util.HashMap;

public class Show {
    private int showId;
    private String title;
    private HashMap<Integer, Actor> actorList = new HashMap<Integer, Actor>();
    private int runTime;
    private Rating ageLimit = new Rating();
    private HashMap<Integer, Genre> genreList = new HashMap<Integer, Genre>();
    private String image;

    public Show(String title, String actor, String time) {
        System.out.println(title + actor + time);
    }


    public Show(int showId, String title, HashMap<Integer, Actor> actorList, int runTime, Rating ageLimit, HashMap<Integer, Genre> genre, String image)
    {
        this.showId = showId;
        this.title = title;
        this.actorList = actorList;
        this.runTime = runTime;
        this.ageLimit = ageLimit;
        this.genreList = genre;
        this.image = image;
    }

    public Show() {

    }


    public int getShowId(){return showId;}

    public void setShowId(int showId){this.showId = showId;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<Integer, Actor> getActorList() {
        return actorList;
    }

    public void setActorList(HashMap<Integer, Actor> actorList) {
        this.actorList = actorList;
    }

    public void addActor(Actor actor) {
        this.actorList.put(actor.getId(), actor);
    }

    public void addGenre(Genre genre) {
        this.genreList.put(genre.getId(), genre);
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public Rating getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Rating ageLimit) {
        this.ageLimit = ageLimit;
    }

    public HashMap<Integer, Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(HashMap<Integer, Genre> genreList) {
        this.genreList = genreList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ShowIntegrityCheck integrityCheck(Show cShow) {
        ShowIntegrityCheck sic = new ShowIntegrityCheck();
        sic.run(this, cShow);

        return sic;
    }
}
