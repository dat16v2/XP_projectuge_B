package Database;

import model.Show;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Martin on 17-03-2017.
 */
public class DatabaseConnection
{
    // Singleton design pattern

    Connection conn = null;
    Statement stmt = null;

    private static DatabaseConnection instance = new DatabaseConnection();

    public static DatabaseConnection getInstance()
    {
        return instance;
    }

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://seq.tf:3307/xp_projectuge_b";

    //  Database credentials
    static final String USER = "xp";
    static final String PASS = "password";

    public DatabaseConnection()
    {
        try
        {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM xp_projectuge_b.`show`;";
            ResultSet rs = stmt.executeQuery(sql);
        }
        catch (Exception ex)
        {
            System.out.println("Does not work." + ex);
        }
    }

    public void add(Show show){

        String title = show.getTitle();
        String actorsList = show.getActorsList();
        int runTime = show.getRunTime();
        String ageLimit = show.getAgeLimit();
        String genre = show.getGenre();
        String image = show.getImage();

        ResultSet rs;
        String sql;
        try {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO `show` (title, runtime, poster_path) VALUES (?, ?, ?)");
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            }
            catch(SQLException ex){
            }
            try{
                if (conn != null){
                    conn.close();
                }
            }
            catch(SQLException se){

            }
        }
    }

    public void deleteShow(Show show){
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            }
            catch(SQLException ex){
            }
            try{
                if (conn != null){
                    conn.close();
                }
            }
            catch(SQLException se){

            }
        }
    }
}

