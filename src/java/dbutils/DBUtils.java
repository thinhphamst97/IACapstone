package dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public static Connection ConnectDB() throws SQLException {
        final String driver = "com.mysql.cj.jdbc.Driver";
        final String url = "jdbc:mysql://dbadmin:dbadmin@localhost:3306/boot";
        Connection c = null;
        try {
            Class.forName(driver);
            c = DriverManager.getConnection(url);
            System.out.println("Connect successfully!!");
        } catch (ClassNotFoundException e) {
            System.out.println("driver: " + driver);
            e.printStackTrace();
        }
        return c;
    }
}
