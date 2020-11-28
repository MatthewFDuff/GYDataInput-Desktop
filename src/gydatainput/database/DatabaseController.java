package gydatainput.database;

import gydatainput.ui.plotpackage.PlotPackageController;

import java.sql.*;

public class DatabaseController {
    private static DatabaseController controller = null;

    private static final String connectionString =
            "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "database=gyPSPPGP;"
            //+ "user=matthew;" Username and password are only required if integratedSecurity=false
            //+ "password=;"
            + "encrypt=false;"
            + "integratedSecurity=true;" // Automatically uses windows credentials (requires .dll in System32)
            + "trustServerCertificate=false;"
            + "loginTimeout=30;";

    private static Connection connection = null;
    private static Statement statement = null;

    private DatabaseController() {
        createConnection();
    }

    public static Connection getConnection() {
        return connection;
    }

    // Singleton
    public static DatabaseController getInstance() {
        if (controller == null) {
            controller = new DatabaseController();
        }

        return controller;
    }

    private void createConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionString);
            System.out.println("Connected to Growth & Yield Database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // A query which returns results from the database.
    static ResultSet executeQuery(String query) {
        ResultSet result;

        try {
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // A query which modifies the database.
    public boolean executeAction(String action) {
        try {
            statement = connection.createStatement();
            statement.execute(action);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
