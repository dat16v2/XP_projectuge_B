package controller;

import Database.DatabaseConnection;
import model.Actor;
import model.Booking;
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


        Booking book = new Booking(3,"Søren", 323212222, 1, 7);

        DatabaseConnection dbconnect = new DatabaseConnection();

        dbconnect.reserve(book);
    }
}
