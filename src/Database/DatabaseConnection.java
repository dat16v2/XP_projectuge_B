package Database;

import model.Actor;
import model.Genre;
import model.Rating;
import model.Show;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Martin on 17-03-2017.
 */
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

    public Show get(int id) {
        Show show = null;
        PreparedStatement STATEMENT_GET_SHOW;
        PreparedStatement STATEMENT_GET_SHOW_ACTOR;
        PreparedStatement STATEMENT_GET_SHOW_GENRE;
        PreparedStatement STATEMENT_GET_SHOW_RATING;
        ResultSet result = null;

        try {
            STATEMENT_GET_SHOW = conn.prepareStatement("SELECT title, runtime, poster_path from `show` WHERE id = ?");
            STATEMENT_GET_SHOW_ACTOR = conn.prepareStatement("SELECT actor.first_name, actor.last_name, actor.id from actor inner join show_actor on actor.id = show_actor.id_actor WHERE show_actor.id_show=?");
            STATEMENT_GET_SHOW_GENRE = conn.prepareStatement("SELECT genre.name, genre.id from genre inner join show_genre on genre.id = show_genre.id_genre WHERE show_genre.id_show=?");
            STATEMENT_GET_SHOW_RATING = conn.prepareStatement("SELECT rating.name, rating.id from rating inner join show_rating on rating.id = show_rating.id_rating WHERE show_rating.id_show=? LIMIT 1");

            // Get initial show data
            STATEMENT_GET_SHOW.setInt(1, id);
            result = STATEMENT_GET_SHOW.executeQuery();

            // Set initial show data
            show.setShowId(id);
            show.setTitle(result.getString(1));
            show.setRunTime(result.getInt(2));
            show.setImage(result.getString(3));

            // Get actors related with show
            STATEMENT_GET_SHOW_ACTOR.setInt(1, show.getShowId());
            result = STATEMENT_GET_SHOW_ACTOR.executeQuery();

            // Add actors to show
            while (result.next()) {
                Actor actor = new Actor();

                actor.setId(result.getInt(3));
                actor.setFirstName(result.getString(1));
                actor.setLastName(result.getString(2));

                show.addActor(actor);
            }

            // Get genres related to show
            STATEMENT_GET_SHOW_GENRE.setInt(1, show.getShowId());
            result = STATEMENT_GET_SHOW_GENRE.executeQuery();

            // Add genres to show
            while(result.next()) {
                Genre genre = new Genre();

                genre.setId(result.getInt(2));
                genre.setName(result.getString(1));
                show.addGenre(genre);
            }

            // Get age rating
            result = STATEMENT_GET_SHOW_RATING.executeQuery();
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
        String actorsList = show.getActorsList();
        int runTime = show.getRunTime();
        String genre = show.getGenre();

        ResultSet rs;
        String sql;
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `show` (title, runtime, poster_path) VALUES (?, ?, ?)");
            ps.setString(1, show.getTitle());
            ps.setInt(2, show.getRunTime());
            ps.setString(3, show.getImage());

            PreparedStatement ps2 = conn.prepareStatement("INSERT INTO `show_genre` (id_genre, id_show) VALUES ( ?, ? );");
            ps2.setInt(1, 1);
            ps2.setInt(2, 2);

            PreparedStatement ps3 = conn.prepareStatement("INSERT INTO `show_actor` (id_actor, id_show) VALUES (?, ?);");
            ps3.setInt(1, 1);
            ps3.setInt(2, 2);

            PreparedStatement ps4 = conn.prepareStatement("INSERT INTO `show_rating` (id_rating, id_show) VALUES (?, ?);");
            ps4.setInt(1, 1);
            ps4.setInt(2, 1);

            //Possibly add boolean result?
            ps.execute();
            ps2.execute();
            ps3.execute();
            ps4.execute();
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

    public void deleteShow(Show show) {
        String title = show.getTitle();
        String actorsList = show.getActorsList();
        int runTime = show.getRunTime();
        String ageLimit = show.getAgeLimit();
        String genre = show.getGenre();
        String image = show.getImage();

        ResultSet rs;
        String sql;
        try {
            //String query = "delete from show where title = ?";
            PreparedStatement preparedStmt = conn.prepareStatement("delete from `show` where title = ?");
            preparedStmt.setString(1, title);
            preparedStmt.execute();
            /*
            PreparedStatement ps = conn.prepareStatement("SELECT `show` WHERE " + title + "(title, runtime, poster_path) VALUES (?, ?, ?)");
            ps.setString(1, title);
            ps.setInt(2, runTime);
            ps.setString(3, image);

            PreparedStatement ps2 = conn.prepareStatement("INSERT INTO `show_genre` (id_genre, id_show) VALUES ( ?, ? );");
            ps2.setInt(1, 1);
            ps2.setInt(2, 2);

            PreparedStatement ps3 = conn.prepareStatement( "INSERT INTO `show_actor` (id_actor, id_show) VALUES (?, ?);");
            ps3.setInt(1, 1);
            ps3.setInt(2,2);

            PreparedStatement ps4 = conn.prepareStatement("INSERT INTO `show_rating` (id_rating, id_show) VALUES (?, ?);");
            ps4.setInt(1,1);
            ps4.setInt(2,1);

            //Possibly add boolean result?
            ps.execute();
            ps2.execute();
            ps3.execute();
            ps4.execute();
            */
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

