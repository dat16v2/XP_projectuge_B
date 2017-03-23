package model;

import java.util.ArrayList;

/**
 * Created by x on 17/03/2017.
 */
public class Show {


    private int showId;
    private String title;
    private String actorsList;
    //private ArrayList<String> actorsList = new ArrayList<String>();
    private int runTime;
    private String ageLimit;
    private String genre;
    private String image;


    public Show(int showId, String title, String actorsList, int runTime, String ageLimit, String genre, String image)
    {
        this.showId = showId;
        this.title = title;
        this.actorsList = actorsList;
        this.runTime = runTime;
        this.ageLimit = ageLimit;
        this.genre = genre;
        this.image = image;
    }


    public int getShowId(){return showId;}

    public void setShowId(int showId){this.showId = showId;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActorsList() {
        return actorsList;
    }

    public void setActorsList(String actors) {
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
