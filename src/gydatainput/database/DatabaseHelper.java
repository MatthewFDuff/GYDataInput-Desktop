package gydatainput.database;

import gydatainput.models.Table;
import gydatainput.models.plotpackage.Package;
import javafx.collections.ObservableList;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The Database Helper class provides the application with all database query functions.
 * The functions are static and can be referenced from other classes, making it easy to
 * perform queries and pass database results to any class that needs it.
 *
 * @author Matthew Duff
 * @version 0.1
 * @since 2020-11-28
 */
public class DatabaseHelper {

    /**
     *
     * @param packageList
     * @return
     */
    public static boolean loadPlotPackages(ObservableList<Package> packageList) {
        String query = "SELECT DISTINCT * FROM dbo.tblPackage"; // Only one entry per package.
        try {
            ResultSet result = DatabaseController.executeQuery(query);

            final ResultSetMetaData meta = result.getMetaData();
            final int columnCount = meta.getColumnCount();

            while (result.next()) {
                JSONObject fields = new JSONObject();

                for (int column = 1; column <= columnCount; column++) {
                    //System.out.println(meta.getColumnClassName(column) + ": " + result.getObject(column));
                    fields.put(meta.getColumnName(column), result.getObject(column));
                }

//                System.out.println(fields);

                Package pkg = new Package(fields);

                packageList.add(pkg);
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param key
     * @param keyName
     * @param tableName
     * @param classType
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static <T extends Table> T getData(int key, String keyName, String tableName, Class<T> classType) throws SQLException {
        String query = "SELECT * FROM dbo." + tableName + " WHERE " + keyName + " = " + key;
        ResultSet results = DatabaseController.executeQuery(query);
        System.out.println(query);

        final ResultSetMetaData meta = results.getMetaData();
        final int columnCount = meta.getColumnCount();
        String newKeyName = "";

        while (results.next()) {

            JSONObject fields = new JSONObject();

            for (int column = 1; column <= columnCount; column++) {
                if (column == 1) {
                    newKeyName = meta.getColumnName(column);
                }

                if (meta.getColumnClassName(column) == "java.sql.Date") {
                    fields.put(meta.getColumnName(column), results.getObject(column).toString());
                } else {
                    fields.put(meta.getColumnName(column), results.getObject(column));
                }
            }

            T obj = null;
            try {
                obj = classType.newInstance();
                obj.setFields(fields);
                System.out.println("Obj Key: " + newKeyName + ": " + (int) fields.get(newKeyName));
                // Important to set the keyname and key so future queries work.
                if (newKeyName != "") {
                    // The first key in the results is the primary key for the table.
                    obj.setKeyName(newKeyName);
                    // Use the first key in the set to get the value of the key.
                    obj.setKey((int) fields.get(newKeyName));
                    obj.fetchData();
                }
//                    System.out.println(obj.getClass() + ": " + getKeyName() + ": " + getKey());

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            return obj;
        }

        return null;
    }

    /**
     *
     * @param key
     * @param keyName
     * @param tableName
     * @param classType
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static <T extends Table> ArrayList<T> getObjects(int key, String keyName, String tableName, Class<T> classType) throws SQLException {
        // Get the total number of results from the query.
        String query = "SELECT COUNT(*) AS total FROM dbo." + tableName + " WHERE " + keyName + " = " + key;

        ResultSet results = DatabaseController.executeQuery(query);
        results.next();
        int count = results.getInt("total");

        // Get the actual results from the query.
        query = "SELECT * FROM dbo." + tableName + " WHERE " + keyName + " = " + key;
        results = DatabaseController.executeQuery(query);
        //System.out.println(results.toString());
        System.out.println(query);

        // Get the number of columns from the query.
        final ResultSetMetaData meta = results.getMetaData();
        final int columnCount = meta.getColumnCount();
        String newKeyName = "";

        ArrayList<T> models = new ArrayList<>();
        while (results.next()) {
            // Get the values which will be stored in the object.
            JSONObject fields = new JSONObject();
            // Iterate through the table results and save the values into the JSON Object.
            for (int column = 1; column <= columnCount; column++) {
                if (column == 1) {
                    newKeyName = meta.getColumnName(column);
                }

                fields.put(meta.getColumnName(column), results.getObject(column));
            }

            T obj = null;
            try {
                obj = classType.newInstance();
                obj.setFields(fields);
                // Important to set the keyname and key so future queries work.
                if (newKeyName != null) {
                    // The first key in the results is the primary key for the table.
                    obj.setKeyName(newKeyName);
                    // Use the first key in the set to get the value of the key.
                    obj.setKey((int) fields.get(newKeyName));
                    obj.fetchData();
                }
//                    System.out.println(obj.getClass() + ": " + getKeyName() + ": " + getKey());

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            // Add the object to the array.
            models.add(obj);
        }

        return models;
    }
}