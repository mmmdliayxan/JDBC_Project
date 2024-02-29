package util;

import interfaces.Checkable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Util {

    public static Connection getHostConnection() throws SQLException {
        return DriverManager.getConnection(Checkable.CONNECTION_HOST_URL, Checkable.USER, Checkable.PASSWORD);
    }

    public static Connection getSchemaConnection() throws SQLException {
        return DriverManager.getConnection(Checkable.CONNECTION_SCHEMA_URL, Checkable.USER, Checkable.PASSWORD);
    }
}
