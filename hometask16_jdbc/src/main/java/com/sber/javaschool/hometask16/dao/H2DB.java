package com.sber.javaschool.hometask16.dao;

import com.sber.javaschool.hometask16.db.DBException;
import com.sber.javaschool.hometask16.db.DbCreateException;
import com.sber.javaschool.hometask16.db.H2DBHelper;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class H2DB implements SourceDao {

    private final Connection connection;

    public H2DB() throws DbCreateException, SQLException {
        try {
            H2DBHelper.createDB();
        } catch (FileNotFoundException | DbCreateException e) {
            e.printStackTrace();
            throw new DbCreateException();
        }
        connection = H2DBHelper.connection();
    }

    @Override
    public void addFibonachi(Map<Integer, Long> calculations) {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into FIBONACHI (NUMBER, RESULT) VALUES ( ?, ? )")) {

            for (var entry : calculations.entrySet()) {
                Integer num = entry.getKey();
                Long res = entry.getValue();
                ps.setInt(1, num);
                ps.setLong(2, res);
                ps.addBatch();
            }

            ps.executeBatch();

        } catch (SQLException exception) {
            throw new DBException("Ошибка вставки в таблицу");
        }
    }

    @Override
    public List<Integer> findFibonachi(int n) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select number, result from FIBONACHI " +
                        "order by number " +
                        "limit ?")) {
            statement.setInt(1, n);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            List<Integer> results = new ArrayList<>();
            while (rs.next()) {
                results.add((int) rs.getLong(2));
            }
            return results;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public long findFibonachiByNumber(int n) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select RESULT from FIBONACHI where NUMBER = ?")) {
            statement.setInt(1, n);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            if (rs.next())
                return rs.getLong(1);
        } catch (SQLException exception) {
            throw new DBException("Ошибка поиска числа в таблице");
        }
        return 0;
    }

    @Override
    public void addFibonachi(int n, long result) {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into FIBONACHI (NUMBER, RESULT) VALUES ( ?,? )")) {
            ps.setInt(1, n);
            ps.setLong(2, result);
            ps.execute();
        } catch (SQLException exception) {
            throw new DBException("Ошибка вставки в таблицу");
        }
    }

    @Override
    public List<Integer> findAll() {
        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(
                    "select number, result from FIBONACHI " +
                            "order by number");

            List<Integer> results = new ArrayList<>();
            int count = 0;
            while (rs.next()) {
                if (rs.getInt(1) != count++) {
                    throw new DBException("DB: db problems");
                }
                results.add((int) rs.getLong(2));
            }
            return results;
        } catch (SQLException exception) {
            throw new DBException("DB:" + exception.getMessage());
        }
    }

    @Override
    public boolean clear() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("drop table FIBONACHI");
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
}
