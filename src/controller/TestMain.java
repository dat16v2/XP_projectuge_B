package controller;

import Database.DatabaseConnection;
import model.Actor;
import model.Genre;
import model.Rating;
import model.Show;

import java.util.ArrayList;

/**
 * Created by Martin on 21-03-2017.
 */
public class TestMain
{
    public static void main(String[] args)
    {
        DatabaseConnection connection = DatabaseConnection.getInstance();


        ArrayList<Actor> listOfActors = new ArrayList<Actor>();
        Rating rating = new Rating();
        rating.setId(1);
        rating.setName("30+");
        Actor actor = new Actor();
        actor.setFirstName("Kurt");
        actor.setId(1);
        Genre genre = new Genre();
        genre.setId(1);
        genre.setName("ComedyMainz");

        // DETTE ER ET EKSEMPEL
        Show show = new Show();
        show.addActor(actor);
        show.addGenre(genre);
        show.setActorList(listOfActors);
        show.setAgeLimit(rating);
        show.setRunTime(180);
        show.setTitle("Juan Million Dollar Baby");
        show.setShowId(1);
        show.setImage("LightsabersHere");
        //show.setGenreList(genres);
        //connection.add(show);
        //connection.deleteShow(show);
        connection.editShow(show, 7);
    }
}
