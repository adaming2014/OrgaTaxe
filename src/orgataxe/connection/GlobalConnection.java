package orgataxe.connection;

import com.sun.istack.internal.Nullable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by INTI0221 on 06/08/2014.
 */
public class GlobalConnection {
    private static final String connectionURL = "jdbc:mysql://localhost:3306/orga_taxe";
    private static final String userName = "root";
    private static final String password = "";

    private static Connection connection;

    /**
     * Create a new connection.
     *
     * @return The new connection. Null is the connection failed.
     */
    private static
    @Nullable
    Connection createConnection() throws SQLException {
        return DriverManager.getConnection(connectionURL, userName, password);
    }

    /**
     * Return the current database connection.
     *
     * @return The current database connection. Null if there is no connection available.
     */
    public static
    @Nullable
    Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = createConnection();
        }

        if (connection != null && connection.isClosed()) {
            connection = createConnection();
        }

        return connection;
    }
}
