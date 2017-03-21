package controller;

import Database.DatabaseConnection;
import model.Show;

/**
 * Created by Martin on 21-03-2017.
 */
public class TestMain
{
    public static void main(String[] args)
    {
        DatabaseConnection connection = DatabaseConnection.getInstance();

        // DETTE ER ET EKSEMPEL
        Show show = new Show(1, "Rogue One", "listOfActors", 180, "18+", "horror", "lightsaberlul");

        connection.add(show);
    }
}
