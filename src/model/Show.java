package model;

/*
CREATE TABLE `show`(
	id INT NOT NULL AUTO_INCREMENT,
	title VARCHAR(250) NOT NULL,
	runtime INT,
	poster_path TEXT,
	last_updated TIMESTAMP,
	id_auditorium INT,
	`date` DATE,
	`time` TIME,
	reserved_seats INT,
	PRIMARY KEY (id)
);
 */


import java.sql.Timestamp;
import java.util.ArrayList;

public class Show {
    private int showId;
    private String title;
    private int runTime;
    private String image;
    private Timestamp last_updated;
    private int id_auditorium;
    private ArrayList<Actor> actorList = new ArrayList<>();
    private ArrayList<Genre> genreList = new ArrayList<>();
    private Rating ageLimit = new Rating();





    //private ArrayList<Actor> actorList = new ArrayList<Actor>();

    //private Rating ageLimit = new Rating();
    //private ArrayList<Genre> genreList = new ArrayList<Genre>();



    public Show(int showId, String title, int runTime, String image, Timestamp last_updated, int id_auditorium)
    {
        this.showId = showId;
        this.title = title;
        this.runTime = runTime;
        this.image = image;
        this.last_updated = last_updated;
        this.id_auditorium = id_auditorium;
    }

    public Show() {

    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String poster_path) {
        this.image = image;
    }

    public Timestamp getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Timestamp last_updated) {
        this.last_updated = last_updated;
    }

    public int getId_auditorium() {
        return id_auditorium;
    }

    public void setId_auditorium(int id_auditorium) {
        this.id_auditorium = id_auditorium;
    }

    public ArrayList<Actor> getActorList() {
        return actorList;
    }

    public void setActorsList(ArrayList<Actor> actorsList) {
        this.actorList = actorsList;
    }

    public ArrayList<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(ArrayList<Genre> genreList) {
        this.genreList = genreList;
    }

    public Rating getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Rating ageLimit) {
        this.ageLimit = ageLimit;
    }

    public void addActor(Actor actor){
        this.actorList.add(actor);
    }

    public void addGenre(Genre genre){
        this.genreList.add(genre);
    }


}
