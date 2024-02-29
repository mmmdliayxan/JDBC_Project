package operation;

import exception.UnKnownDatabaseName;
import interfaces.Checkable;
import interfaces.Selectable;
import util.Util;

import java.sql.*;

public class DBOperation {
    public void createDatabase() throws SQLException {
        try (Connection connection = Util.getHostConnection();
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(Selectable.CREATE_DATABASE);
        }
    }
}
