package controller;

import Database.DatabaseConnection;
import model.Actor;
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

        // DETTE ER ET EKSEMPEL
        ArrayList<Actor> actors = new ArrayList<>();
        Actor actor = new Actor();
        actor.setId(1);

        actors.add(actor);

        //Rating

        //Show show = new Show(1, "Rogue One", actors, 180, "18+", "horror", "lightsaberlul");

        //connection.add(show);
        //connection.deleteShow(show);
    }
}
