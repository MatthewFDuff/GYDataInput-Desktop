package gydatainput.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * The Database Controller class manages the actual connection to the database.
 * Note that the controller is a singleton and is initialized by the MainController
 * using the getInstance() function. Database queries are contained in the Database
 * Helper class.
 *
 * @author Matthew Duff
 * @version 0.1
 * @since 2020-11-28
 */
public class DatabaseController {
    // The DatabaseController is a singleton, only referenced for initialization.
    private static DatabaseController controller = null;
    private static Connection connection = null;
    private static Statement statement = null;

    /** Database Controller Constructor
     *      When we create a Database Controller, we initialize
     *      a connection to the database.
     * */
    private DatabaseController() {
        createConnection();
    }

    /** Get Connection
     *
     * */
    public static Connection getConnection() {
        return connection;
    }

    /** Get Instance
     *      This static function allows us to initialize the Database Controller
     *      as a Singleton from the MainController class (on application startup).
     * @return DatabaseController instance.
     * */
    public static DatabaseController getInstance() {
        // If the Database Controller hasn't been created yet..
        if (controller == null) {
            // Initialize a new Database Controller.
            controller = new DatabaseController();
        }

        return controller;
    }

    /** Create Connection
     *      This function sets up the connection to the database, which
     *      is SQLExpress and requires the SQLServerDriver.
     * @return void
     * */
    private void createConnection() {
        try {
            // Setup a FileInputStream to read the properties from the config.properties file and store them in props.
            Properties props = new Properties();
            // TODO src/gydatainput/database/ needs to be removed for production
            FileInputStream in = new FileInputStream("src/gydatainput/database/config.properties");
            props.load(in);
            in.close();

            // Set the driver to SQLServerDriver
            String driver = props.getProperty("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            if (driver != null) {
                Class.forName(driver);
            }

            // Create the database connection string with the settings in config.properties file
            String url = props.getProperty("jdbc.host")
                    + ";database=" + props.getProperty("jdbc.database")
                    + ";encrypt=" + props.getProperty("jdbc.encrypt")
                    + ";integratedSecurity=" + props.getProperty("jdbc.integratedSecurity")
                    + ";trustServerCertificate=" + props.getProperty("jdbc.trustServerCertificate")
                    + ";loginTimeout=" + props.getProperty("jdbc.loginTimeout") + ";";

            connection = DriverManager.getConnection(url);

            System.out.println("Connected to Growth & Yield Database");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** Execute Query
     *      This function is used when executing a query where a result
     *      is expected to be returned.
     * @param query The SQL query as a single string.
     * @return ResultSet containing the results of the query.
     * @see ResultSet
     * */
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

    /** Execute Query
     *      This function is used when executing a query where no
     *      results are expected and instead an action is performed
     *      that modifies the database (UPDATE, ALTER, CREATE, etc).
     * @param action The SQL query as a single string.
     * @return boolean representing the success or failure of the query.
     * */
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
