package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Martin on 17-03-2017.
 */
public class DatabaseConnection
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://seq.tf:3307/xp_projectuge_b";

    //  Database credentials
    static final String USER = "xp";
    static final String PASS = "password";

    public static void main(String[] args)
    {
        Connection conn = null;
        Statement stmt = null;


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
}
