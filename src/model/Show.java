package model;

import java.util.ArrayList;

/**
 * Created by x on 17/03/2017.
 */
public class Show {


    private String title;
    private ArrayList<String> actorsList = new ArrayList<String>();
    private int runTime;
    private String ageLimit;
    private String genre;
    private String image;

    public Show(String title, ArrayList<String> actorsList, int runTime, String ageLimit, String genre, String image)
    {
        this.title = title;
        this.actorsList = actorsList;
        this.runTime = runTime;
        this.ageLimit = ageLimit;
        this.genre = genre;
        this.image = image;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getActorsList() {
        return actorsList;
    }

    public void setActorsList(ArrayList<String> actors) {
        this.actorsList = actors;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public String getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(String ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
