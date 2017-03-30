package model;

import Database.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by x on 17/03/2017.
 */
public class ShowList
{
    ArrayList<Show> listOfShows = new ArrayList<Show>();

    public void add(int showId, String title, HashMap<Integer, Actor> actorsList, int runTime, Rating ageLimit, HashMap<Integer, Genre> genre, String image) {

        Show show = new Show(showId, title, actorsList, runTime, ageLimit, genre, image);
        // TODO: Add to database here

        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();

        databaseConnection.add(show);

        listOfShows.add(show);
    }

    public void delete()
    {

    }

    public void edit()
    {

    }

    public void getListOfShow()
    {

    }

}
