package model;

import java.util.ArrayList;

public class Show {
    private int showId;
    private String title;
    private ArrayList<Actor> actorList = new ArrayList<Actor>();
    private int runTime;
    private Rating ageLimit;
    private ArrayList<Genre> genreList = new ArrayList<Genre>();
    private String image;


    public Show(int showId, String title, ArrayList<Actor> actorList, int runTime, Rating ageLimit, ArrayList<Genre> genre, String image)
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

    public ArrayList<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(ArrayList<Actor> actorList) {
        this.actorList = actorList;
    }

    public void addActor(Actor actor) {
        this.actorList.add(actor);
    }

    public void addGenre(Genre genre) {
        this.genreList.add(genre);
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

    public ArrayList<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
