package com.exortions.core.sql;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public abstract class MySQLDatabase {

    private String database;
    private String host;
    private String port;

    private String username;
    private String password;

    private String url;

    private Connection connection;

    public MySQLDatabase createConnection() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
        return this;
    }

    public ResultSet query(String sql) {
        try {
            return connection.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean execute(String sql) {
        try {
            return connection.prepareStatement(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
