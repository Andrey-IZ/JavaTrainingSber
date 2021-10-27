package com.sber.javaschool.hometask16.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class H2DBHelper {
    public static Connection connection() throws SQLException {
        final Connection connection = DriverManager.getConnection("jdbc:h2:file:~/test", "sa", "");
        connection.setAutoCommit(true);
        return connection;
    }

    public static boolean createDB() throws FileNotFoundException, DbCreateException {
        var classloader = Thread.currentThread().getContextClassLoader();
        var is = classloader.getResourceAsStream("tables.sql");
        if (is == null)
            return false;

        var streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        String sql = new BufferedReader(streamReader).lines().collect(Collectors.joining());
        createDb(sql);
        return true;
    }

    public static void createDb(String sql) throws DbCreateException {
        try (PreparedStatement ps = connection().prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DbCreateException();
        }
    }
}
