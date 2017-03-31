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

        Show logan = connection.get(2);
        System.out.println(logan.getTitle());

        Show test = connection.get(2);
        ArrayList<Actor> actorList = test.getActorList();
        if(actorList.size() > 0){
            for (int i = 0; i < actorList.size(); i++){
                System.out.println(actorList.get(i).getFirstName() + " " + actorList.get(i).getLastName());
            }
        } else {
            System.out.println("time to panic");
        }


        Rating rating = new Rating();
        rating.setId(20);
        rating.setName("30+");

        Actor ole = new Actor();
        ole.setFirstName("Ole");
        ole.setLastName("thestrup");
        ole.setId(20);

        Actor mads = new Actor();
        mads.setFirstName("mads");
        mads.setLastName("mikkelsen");
        mads.setId(21);

        Genre genre = new Genre();
        genre.setId(20);
        genre.setName("tentancles");

        // DETTE ER ET EKSEMPEL
        Show show = new Show();
        show.setAgeLimit(rating);
        show.addActor(mads);
        show.addActor(ole);
        show.addGenre(genre);
        show.setRunTime(189);
        show.setTitle("Eventyr med mads og ole");
        show.setShowId(90);
        show.setImage("bebopbeop");
        //connection.add(show);
    }
}
