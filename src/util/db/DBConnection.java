
package util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {

    private DBConnection() {
    }
    private static boolean isConnected = false;
    private static Connection connection;

    public static Connection getConnection() {
        return isConnected ? connection : null;
    }

    public static Connection establishConnection(String username, String password)
            throws SQLException {
        String connString = "jdbc:oracle:thin:@localhost:1521:XE";
        connection = DriverManager.getConnection(connString, username, password);
        isConnected = connection.isValid(0);
        return connection;
    }
}
