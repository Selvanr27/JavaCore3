package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectivity {

    private final Connection connection;

    public Connectivity() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_app",
                "root",
                "password"
        );
        connection.setAutoCommit(false);
        System.out.println("----DB Connection Success !-----");
    }

    public Connection getConnection() {
        return connection;
    }
}