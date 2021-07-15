package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private Connection dbconnection;

    public Connection getConnection(){
        String dbhost = "localhost";
        String dbport = "3306";
        String dbuser = "root";
        String dbpass = "conan-kun";
        String dbname = "studentlogin";

        String connectionString = "jdbc:mysql://"+dbhost + ":"+dbport+"/"+dbname+"?autoReconnect-true&userSSL=false";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbconnection = DriverManager.getConnection(connectionString,dbuser,dbpass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbconnection;
    }

}
