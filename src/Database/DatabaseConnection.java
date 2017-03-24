package Database;

import model.*;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    // Singleton design pattern

    Connection conn = null;
    Statement stmt = null;

    private static DatabaseConnection instance = new DatabaseConnection();

    public static DatabaseConnection getInstance() {
        return instance;
    }

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://seq.tf:3307/xp_projectuge_b";

    //  Database credentials
    static final String USER = "xp";
    static final String PASS = "password";

    public DatabaseConnection() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            //System.out.println("Creating statement...");
            //stmt = conn.createStatement();
            //String sql;
            //sql = "SELECT * FROM xp_projectuge_b.`show`;";
            //ResultSet rs = stmt.executeQuery(sql);
        } catch (Exception ex) {
            System.out.println("Does not work." + ex);
        }
    }

    public void reserveDB(Reserve reserve, Object[] reserveDBArray){
        reserveDBArray = reserve.getSeatNumber();
        PreparedStatement statementInsertSeat;
        try{
        for(int i = 1; i < reserveDBArray.length; i++) {

            statementInsertSeat = conn.prepareStatement("UPDATE `reserve` SET seatNumber WHERE id = " + i);
            statementInsertSeat.executeUpdate();

        }

        } catch (SQLException e){

        }
        finally {
            try{
                if(conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {

            }
            try{
                if(stmt != null) {
                    stmt.close();
                }
            }
            catch(SQLException e){

            }
        }
    }

    public Show get(int id) {
        Show show = null;
        PreparedStatement statementGetShow;
        PreparedStatement statementGetShowActor;
        PreparedStatement statementGetShowGenre;
        PreparedStatement statementGetShowRating;
        ResultSet result = null;

        try {
            statementGetShow = conn.prepareStatement("SELECT title, runtime, poster_path from `show` WHERE id = ?");
            statementGetShowActor = conn.prepareStatement("SELECT actor.first_name, actor.last_name, actor.id from actor inner join show_actor on actor.id = show_actor.id_actor WHERE show_actor.id_show=?");
            statementGetShowGenre = conn.prepareStatement("SELECT genre.name, genre.id from genre inner join show_genre on genre.id = show_genre.id_genre WHERE show_genre.id_show=?");
            statementGetShowRating = conn.prepareStatement("SELECT rating.name, rating.id from rating inner join show_rating on rating.id = show_rating.id_rating WHERE show_rating.id_show=? LIMIT 1");

            // Get initial show data
            statementGetShow.setInt(1, id);
            result = statementGetShow.executeQuery();

            // Set initial show data
            show.setShowId(id);
            show.setTitle(result.getString(1));
            show.setRunTime(result.getInt(2));
            show.setImage(result.getString(3));

            // Get actors related with show
            statementGetShowActor.setInt(1, show.getShowId());
            result = statementGetShowActor.executeQuery();

            // Add actors to show
            while (result.next()) {
                Actor actor = new Actor();

                actor.setId(result.getInt(3));
                actor.setFirstName(result.getString(1));
                actor.setLastName(result.getString(2));

                show.addActor(actor);
            }

            // Get genres related to show
            statementGetShowGenre.setInt(1, show.getShowId());
            result = statementGetShowGenre.executeQuery();

            // Add genres to show
            while(result.next()) {
                Genre genre = new Genre();

                genre.setId(result.getInt(2));
                genre.setName(result.getString(1));
                show.addGenre(genre);
            }

            // Get age rating
            result = statementGetShowRating.executeQuery();
            Rating rating = new Rating();
            rating.setName(result.getString(1));
            rating.setId(result.getInt(2));

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        return show;
    }

    public void add(Show show) {
        PreparedStatement statementInsertShow;
        PreparedStatement statementInsertShowActor;
        PreparedStatement statementInsertShowGenre;
        PreparedStatement statementInsertShowRating;

        try {
            statementInsertShow = conn.prepareStatement("INSERT INTO `show` (title, runtime, poster_path) VALUES  (? ,? ,?)");

            statementInsertShow.setString(1, show.getTitle());
            statementInsertShow.setInt(2, show.getRunTime());
            statementInsertShow.setString(3, show.getImage());

            boolean result = statementInsertShow.execute();

            if (!result) { // Handle this at some point... TODO
                System.out.println("Uh ohh... check");
            }

            // Add actors to show
            statementInsertShowActor = conn.prepareStatement("INSERT into show_actor (id_actor, id_show) VALUES (?, ?)");
            for (int i = 0; i < show.getActorList().size(); i++) {
                Actor actor = show.getActorList().get(i);

                statementInsertShowActor.setInt(1, actor.getId());
                statementInsertShowActor.setInt(2, show.getShowId());

                if (!(i + 1 == show.getActorList().size())) {
                    statementInsertShowActor.addBatch();
                }
            }

            result = statementInsertShowActor.execute();

            if (!result) { // Handle this at some point... TODO
                System.out.println("Uh ohh...");
            }

            // Add genres to show
            statementInsertShowGenre = conn.prepareStatement("INSERT into show_genre (id_genre, id_show) VALUES (?, ?)");
            for (int i = 0; i < show.getGenreList().size(); i++) {
                Genre genre = show.getGenreList().get(i);

                statementInsertShowGenre.setInt(1, genre.getId());
                statementInsertShowGenre.setInt(2, show.getShowId());

                if (!(i + 1 == show.getGenreList().size())) {
                    statementInsertShowGenre.addBatch();
                }
            }

            result = statementInsertShowActor.execute();

            if (!result) { // Handle this at some point... TODO
                System.out.println("Uh ohh...");
            }

            statementInsertShowRating = conn.prepareStatement("INSERT INTO show_rating (id_rating, id_show) VALUES (?, ?)");
            statementInsertShowRating.setInt(1, show.getAgeLimit().getId());
            statementInsertShowRating.setInt(2, show.getShowId());

            result = statementInsertShowRating.execute();

            if (!result) { // Handle this at some point... TODO
                System.out.println("Uh ohh...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {

            }
        }
    }

    public boolean deleteShow(Show show) {
        try {
            PreparedStatement preparedStmt = conn.prepareStatement("DELETE FROM `show` WHERE id = ?");
            preparedStmt.setInt(1, show.getShowId());
            return preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {

            }
        }

        return false;
    }

    public void editShow(Show show, int ID){
        PreparedStatement statementEditShow;
        PreparedStatement statementEditShowActor;
        PreparedStatement statementEditShowGenre;
        PreparedStatement statementEditShowRating;
        try {
            statementEditShow = conn.prepareStatement("UPDATE `show` SET title = ?, runtime = ?, poster_path = ? WHERE id = ?");

            statementEditShow.setString(1, show.getTitle());
            statementEditShow.setInt(2, show.getRunTime());
            statementEditShow.setString(3, show.getImage());
            statementEditShow.setInt(4, ID);


            boolean result = statementEditShow.execute();

            if (!result) { // Handle this at some point... TODO
                System.out.println("Uh ohh...");
            }

            // Add actors to show
            statementEditShowActor = conn.prepareStatement("UPDATE show_actor SET id_actor = ? WHERE id_show = ?");
            for (int i = 0; i < show.getActorList().size(); i++) {
                Actor actor = show.getActorList().get(i);

                statementEditShowActor.setInt(1, actor.getId());
                statementEditShowActor.setInt(2, show.getShowId());

                if (!(i + 1 == show.getActorList().size())) {
                    statementEditShowActor.addBatch();
                }
            }

            result = statementEditShowActor.execute();

            if (!result) { // Handle this at some point... TODO
                System.out.println("Uh ohh...");
            }

            // Add genres to show
            statementEditShowGenre = conn.prepareStatement("UPDATE show_genre SET id_genre = ? WHERE id_show = ?");
            for (int i = 0; i < show.getGenreList().size(); i++) {
                Genre genre = show.getGenreList().get(i);

                statementEditShowGenre.setInt(1, genre.getId());
                statementEditShowGenre.setInt(2, show.getShowId());

                if (!(i + 1 == show.getGenreList().size())) {
                    statementEditShowGenre.addBatch();
                }
            }

            result = statementEditShowActor.execute();

            if (!result) { // Handle this at some point... TODO
                System.out.println("Uh ohh...");
            }

            statementEditShowRating = conn.prepareStatement("UPDATE show_rating SET id_rating = ? WHERE id_show = ?");
            statementEditShowRating.setInt(1, show.getAgeLimit().getId());
            statementEditShowRating.setInt(2, show.getShowId());

            result = statementEditShowRating.execute();

            if (!result) { // Handle this at some point... TODO
                System.out.println("Uh ohh...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {

            }
        }
    }
}

