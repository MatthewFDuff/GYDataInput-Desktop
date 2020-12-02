package gydatainput.database;

import java.sql.*;

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

    // Separate file TODO
    private static final String connectionString =
            "jdbc:sqlserver://localhost\\SQLEXPRESS;"
            + "database=gyPSPPGP;"
            + "encrypt=false;"
            + "integratedSecurity=true;" // Automatically uses windows credentials (may require .dll in System32)
            + "trustServerCertificate=false;"
            + "loginTimeout=30;";

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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionString);
            System.out.println("Connected to Growth & Yield Database");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
