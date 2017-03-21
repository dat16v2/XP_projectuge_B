package model;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by x on 17/03/2017.
 */
public class ShowList
{
    ArrayList<Show> listOfShows = new ArrayList<Show>();

    public void add(String title, ArrayList<String> actorsList, int runTime, String ageLimit, String genre, String image) {
        Show show = new Show(title, actorsList, runTime, ageLimit, genre, image);
        // TODO: Add to database here
        /* TODO: set showId efter opretttelse i database
            INSERT INTO `show` (title, runtime, poster_path) VALUES ('Saving Private Ryan', 180, 'imgs/spr180px.jpg');
            SET @show_id = LAST_INSERT_ID();
            INSERT INTO `show_genre` (id_genre, id_show) VALUES (4, @show_id); # ADD GENRE
            INSERT INTO `show_actor` (id_actor, id_show) VALUES (3, @show_id); # ADD ACTOR
            INSERT INTO `show_actor` (id_actor, id_show) VALUES (4, @show_id); # ADD ACTOR
            INSERT INTO `show_rating` (id_rating, id_show) VALUES (5, @show_id); # ADD RATING
         */
        listOfShows.add(show);
    }

    // runs on startup to fill list from database
    public void add(int showId, String title, ArrayList<String> actorsList, int runTime, String ageLimit, String genre, String image) {
        Show show = new Show(showId,title, actorsList, runTime, ageLimit, genre, image);
        // TODO: Add to database here
        /*
            INSERT INTO `show` (title, runtime, poster_path) VALUES ('Saving Private Ryan', 180, 'imgs/spr180px.jpg');
            SET @show_id = LAST_INSERT_ID();
            INSERT INTO `show_genre` (id_genre, id_show) VALUES (4, @show_id); # ADD GENRE
            INSERT INTO `show_actor` (id_actor, id_show) VALUES (3, @show_id); # ADD ACTOR
            INSERT INTO `show_actor` (id_actor, id_show) VALUES (4, @show_id); # ADD ACTOR
            INSERT INTO `show_rating` (id_rating, id_show) VALUES (5, @show_id); # ADD RATING
         */
        listOfShows.add(show);
    }

    public void delete(Show show)
    {

    }

    public void edit(Show originalShow)
    {

    }

    public ArrayList<Show> getListOfShow()
    {
        return listOfShows;
    }

}
