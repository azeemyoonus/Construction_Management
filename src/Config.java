import java.sql.*;

public class Config {

    public Connection dbConnect() {
        Connection con;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/Construction_Management", "postgres", "Azeem@12");
            return con;
        } catch (Exception error) {
            System.out.println(error);
            return null;
        }
        
    }

}
