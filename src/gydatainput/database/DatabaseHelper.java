package gydatainput.database;

import gydatainput.models.Table;
import gydatainput.models.plotpackage.Package;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

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
        // Get all packages from the database where the VisitType is 2 or 4
        String query = "SELECT DISTINCT * FROM dbo.tblPackage"; // Only one entry per package.
//        String query = "SELECT p.[PackageKey]" +
//                "      ,[PlotKey]" +
//                "      ,[StartYear]" +
//                "      ,[ApproachCode]" +
//                "      ,[CoOpMethod]" +
//                "  FROM [gyPSPPGP].[dbo].[tblPackage] as p" +
//                "  INNER JOIN tblVisit as t ON (p.PackageKey = t.PackageKey)" +
//                "  WHERE VisitTypeCode = 2 OR VisitTypeCode = 4";
        try {
            // The results are stored in a ResultSet, in this case we expect multiple results.
            ResultSet result = DatabaseController.executeQuery(query);

            // ResultSet MetaData allows us to get the total number of columns and column names/types.
            final ResultSetMetaData meta = result.getMetaData();
            // The column count is used to iterate through all fields in the table.
            final int columnCount = meta.getColumnCount();

            // But first we iterate through each result in the ResultSet.
            while (result.next()) {
                // Fields from the table are stored in a JSONArray. See Table class for more detail.
                JSONArray fields = new JSONArray();

                // Now we iterate through each column, which can be considered a single field.
                for (int column = 1; column <= columnCount; column++) {
                    // The fields are stored in JSONObjects, as key-value pairs.
                    JSONObject field = new JSONObject();
                    // The column name becomes the key, and the result itself is the value of the field.
                    field.put(meta.getColumnName(column), result.getObject(column));

                    // The field is then added to the JSONArray of fields.
                    fields.add(field);
                }

                // And a new package is created using the resulting fields JSONArray.
                Package pkg = new Package(fields);
                // This new package needs to be added to the observable list of packages.
                packageList.add(pkg);
            }

            return true;
        } catch (SQLException e) {
            // The packages were unable to be loaded from the database.
            // Most likely error is not being able to connect,
            // or the wrong database is being connected to and doesn't have tblPackage
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
        // In this case we're only looking for one result, a.k.a one row in the table.
        String query = "SELECT * FROM dbo." + tableName + " WHERE " + keyName + " = " + key;
        //System.out.println(query);
        ResultSet results = DatabaseController.executeQuery(query);

        final ResultSetMetaData meta = results.getMetaData();
        final int columnCount = meta.getColumnCount();
        String newKeyName = "";

        while (results.next()) {

            JSONArray fields = new JSONArray();

            for (int column = 1; column <= columnCount; column++) {
                JSONObject field = new JSONObject(); // Create a field object to hold the key/value pair.

                // The first column is always the primary key.
                if (column == 1) {
                    newKeyName = meta.getColumnName(column);
                }

                // If the current field is a date, it needs to be turned into a string.
                if (meta.getColumnClassName(column) == "java.sql.Date") {
                    field.put(meta.getColumnName(column), results.getObject(column).toString());
                } else {
                    field.put(meta.getColumnName(column), results.getObject(column));
                }

                // Add the field object to the fields array.
                fields.add(field);
            }

            // Create an object extended from Table which allows us to create a specific instance of classType.
            T obj = null; // Represents a single row from the database table.
            Object newKey = 0;
            try {
                obj = classType.newInstance();
                obj.setFields(fields); // Set the fields of the instance.

                // Important to set the keyname and key so future queries work.
                if (newKeyName != "") {
                    // The first key in the results is the primary key for the table.
                    obj.setKeyName(newKeyName);
                    // Use the first key in the set to get the value of the key.

                    // Get the first key from the fields array.
                    Iterator<JSONObject> iterator = fields.iterator();
                    // Check that there are fields.
                    while (iterator.hasNext()) {
                        // Get the first field.
                        JSONObject fieldJSON = iterator.next();
                        // Check if the field matches the one being requested.
                        if (fieldJSON.get(newKeyName) != null) {
                            // Set the new key to the value associated with newKeyName
                             newKey = fieldJSON.get(newKeyName);
                        }
                    }

                    // Convert the key to an int and set it in the object instance.
                    obj.setKey((int) newKey);
                    // Update the object, this will be different based on the type.
                    obj.fetchData();
                }

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
        //System.out.println(query);
        results = DatabaseController.executeQuery(query);

        // Get the number of columns from the query.
        final ResultSetMetaData meta = results.getMetaData();
        final int columnCount = meta.getColumnCount();
        String newKeyName = "";

        // models stores all objects, meaning multiple rows in the database.
        ArrayList<T> models = new ArrayList<>();
        // Iterate through each database row (result)
        while (results.next()) {
            // Get the values which will be stored in the object.
            JSONArray fields = new JSONArray();
            // Iterate through the table results and save the values into the JSON Object.
            for (int column = 1; column <= columnCount; column++) {
                // Create an object to store the field.
                JSONObject fieldJSON = new JSONObject();

                String fieldKey = meta.getColumnName(column);
                Object fieldValue = results.getObject(column);

                // If it's the first column, we pull the key name.
                if (column == 1) {
                    newKeyName = fieldKey;
                }

                // Add the field key-value pair to the field JSONObject
                fieldJSON.put(fieldKey, fieldValue);
                // Add the field JSONObject to the fields JSONArray
                fields.add(fieldJSON);
            }

            // Create an object to store the values from the database.
            T obj = null;
            Object newKey = 0;
            try {
                obj = classType.newInstance();
                obj.setFields(fields);

                // Important to set the keyname and key so future queries work.
                if (newKeyName != null) {
                    // The first key in the results is the primary key for the table.
                    obj.setKeyName(newKeyName);
                    // Use the first key in the set to get the value of the key.

                    // Get the first key from the fields array.
                    Iterator<JSONObject> iterator = fields.iterator();
                    // Check that there are fields.
                    while (iterator.hasNext()) {
                        // Get the first field.
                        JSONObject fieldJSON = iterator.next();
                        // Check if the field matches the one being requested.
                        if (fieldJSON.get(newKeyName) != null) {
                            // Set the new key to the value associated with newKeyName
                            newKey = fieldJSON.get(newKeyName);
                        }
                    }

                    // Convert the key to an int and set it in the object instance.
                    obj.setKey((int) newKey);
                    obj.fetchData();

//                    obj.setKey((int) obj.getFromFields(newKeyName));
//                    System.out.println("DatabaseHelper L231: Key" + obj.getKey());
//                    obj.fetchData();
                }

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

    // checks if the data is in the db already and inserts or updates accordingly.
    public static boolean uploadData(int key, String keyName, String tableName, JSONArray fields) throws SQLException {
        // Check to see if the data already exists within the database.
        // Create the query to find the data within the corresponding table.
        String query = "SELECT * FROM dbo.tbl" + tableName + " WHERE " + keyName + " = " + key;
        // System.out.println("uploadData: " + query); // TODO SOUT

        // Execute the query and store the results.
        ResultSet result = DatabaseController.executeQuery(query);

        // Get the metadata of the query results, which allows us to determine column types.
        final ResultSetMetaData meta = result.getMetaData();
        final int columnCount = meta.getColumnCount();

        // Iterate to the first (and only) result in the ResultSet.
        if (result.next()) {
            // Result found. Update the row.
            return updateRowData(key, keyName, tableName, fields, meta, result);

        } else {
            // No results. Insert a new row.
            return insertRowData(key, keyName, tableName, fields, meta);
        }
    }

    // insert data
    public static boolean insertRowData(int key, String keyName, String tableName, JSONArray fields, ResultSetMetaData types) throws SQLException {
        // Set a save point for the transaction.
       // Savepoint save = DatabaseController.getConnection().setSavepoint();

        try {
            String query = buildInsertQuery(tableName, fields);
            System.out.println(query); // TODO SOUT

// Prepare the query.
            PreparedStatement statement = DatabaseController.getConnection().prepareStatement(query);

            // Bind the values to the prepared statement variables.
            Iterator<JSONObject> fieldIterator = fields.iterator();
            for(int i = 1; i <= fields.size(); i++) {

                // Get the variable type of the field value.
                String type = types.getColumnTypeName(i);

                // Get the value from fields.
                JSONObject fieldJSON = (JSONObject) fieldIterator.next();
                String fieldKey = (String) fieldJSON.keySet().iterator().next();
                Object field = fieldJSON.get(fieldKey);

                if (field != null) {
                    // Insert the associate variable type into the prepared statement.
                    switch(type) {
                        case "int":
                        case "tinyint":
                        case "smallint":
                            // Integer
                            statement.setInt(i, (int) (long) field);
                            break;

                        case "decimal":
                            // Double/Float
                            statement.setDouble(i, (double) field);
                            break;

                        case "char":
                        case "varchar":
                            // String
                            statement.setString(i, field.toString());
                            break;

                        case "bit":
                            // Boolean
                            if (field.toString().equals("null"))
                            {
                                statement.setNull(i, Types.BIT);
                            } else {
                                statement.setBoolean(i, field.toString().equals("true"));
                            }
                            break;
                        case "date":
                            // Date
                            statement.setDate(i, Date.valueOf(field.toString()));
                    }
                }
            }

        } catch (SQLException e) {
            // Unable to insert new row, which likely indicates an incorrect data type.
//            try {
                // We need to revert any changes that have been made.
                //DatabaseController.getConnection().rollback(save);
//            } catch (SQLException rollbackException) {
//                // Unable to rollback the changes.. this is worst possible scenario and should never occur.
//                return false;
//            }

            return false;
        }

        return true;
    }

    // Updata data
    public static boolean updateRowData(int key, String keyName, String tableName, JSONArray fields, ResultSetMetaData types, ResultSet data) throws SQLException {
        // Set a save point for the transaction.
        //Savepoint save = DatabaseController.getConnection().setSavepoint();

//        try {
            // Check which columns need to be updated by comparing old and new values.
            ArrayList<String> columns = getUpdatedColumns(fields, data);

            // Construct the query to update the row data.
            String query = buildUpdateQuery(key, keyName, tableName, columns);
            System.out.println(query); // TODO SOUT

            // Prepare the query.
            PreparedStatement statement = DatabaseController.getConnection().prepareStatement(query);

            // Bind the values to the prepared statement variables.
            Iterator<JSONObject> fieldIterator = fields.iterator();

            // Iterate through the fields.
            int fieldIndex = 1;
            int preparedValueIndex = 0;
            while(fieldIterator.hasNext()) {
                JSONObject fieldJSON = fieldIterator.next();

                // Get the variable type of the field value.
                String type = types.getColumnTypeName(fieldIndex);

                // Get the first key-value pair (field) from the JSONObject.
                String fieldKey = (String) fieldJSON.keySet().iterator().next();
                Object fieldValue = fieldJSON.get(fieldKey);

                // Ignore key values which cannot be changed.
                if (!fieldKey.contains("Key")){
                    preparedValueIndex++;


                    // Insert the associate variable type into the prepared statement.
                    switch (type) {
                        case "int":
                            if (fieldValue == null) {
                                statement.setNull(preparedValueIndex, Types.INTEGER);
                                break;
                            }
                        case "tinyint":
                            if (fieldValue == null) {
                                statement.setNull(preparedValueIndex, Types.TINYINT);
                                break;
                            }
                        case "smallint":
                            // Integer
                            if (fieldValue == null) {
                                statement.setNull(preparedValueIndex, Types.SMALLINT);
                            } else {
                                statement.setInt(preparedValueIndex, (int) (long) fieldValue);
                            }
                            break;

                        case "decimal":
                            // Double/Float
                            if (fieldValue == null) {
                                statement.setNull(preparedValueIndex, Types.DOUBLE);
                            } else {
                                statement.setDouble(preparedValueIndex, (double) fieldValue);
                            }
                            break;

                        case "char":
                            if (fieldValue == null) {
                                statement.setNull(preparedValueIndex, Types.CHAR);
                                break;
                            }
                        case "varchar":
                            // String
                            if (fieldValue == null) {
                                statement.setNull(preparedValueIndex, Types.VARCHAR);
                            } else {
                                statement.setString(preparedValueIndex, fieldValue.toString());
                            }
                            break;

                        case "bit":
                            // Boolean
                            if (fieldValue == null) {
                                statement.setNull(preparedValueIndex, Types.BIT);
                            } else {
                                statement.setBoolean(preparedValueIndex, fieldValue.toString().equals("true"));
                            }
                            break;
                        case "date":
                            // Date
                            if (fieldValue == null) {
                                statement.setNull(preparedValueIndex, Types.DATE);
                            } else {
                                statement.setDate(preparedValueIndex, Date.valueOf(fieldValue.toString()));
                            }
                            break;
                        default:
                            System.out.printf("A new sql type has been discovered!" + type);
                    }
                }

                fieldIndex++;
            }

            // Execute the query.
            statement.executeUpdate();

            return true;
//        }
//        catch (SQLException updateException) {
//            // Unable to update the row data, which likely indicates an incorrect data type.
////            try {
//                // We need to revert any changes that have been made.
//                //DatabaseController.getConnection().rollback(save);
////            } catch (SQLException rollbackException) {
////                // Unable to rollback the changes.. this is worst possible scenario and should never occur.
////                return false;
////            }
//
//            return false;
//        }
    }

    // Compares all values within the array and result set to determine which values have changed.
    private static ArrayList<String> getUpdatedColumns(JSONArray fields, ResultSet data) throws SQLException {
        ArrayList<String> updated = new ArrayList<>();

        // Iterate through all data
        for (int i = 0; i < fields.size(); i++) {
            JSONObject field = (JSONObject) fields.get(i);
            Iterator<String> fieldNameKey = field.keySet().iterator();
            String fieldName = fieldNameKey.next();

            if (!fieldName.contains("Key")) {
                updated.add(fieldName);
            }
        }

        return updated;
    }

    // Constructs the string to update an existing row of data in the database.
    // Used for building PreparedStatements.
    private static String buildUpdateQuery(int key, String keyName, String tableName, ArrayList<String> columns) {
        // eg. UPDATE Table
        String query = "UPDATE dbo.tbl" + tableName;

        // eg. SET Column1 = ?, Column2 = ?, Column3 = ?
        query += " SET ";

        for(int i = 0; i < columns.size(); i++) {
            query += columns.get(i) + " = ?, ";
        }
        // Remove the final trailing comma + space;
        query = query.substring(0, query.length() - 2);

        // eg. WHERE PrimaryKey = 1;
        query += " WHERE " + keyName + " = " + key + ";";

        return query;
    }

    // Constructs the string to insert a new row of data into the database.
    // Used for building PreparedStatements.
    public static String buildInsertQuery(String tableName, JSONArray fields) {
        String query = "INSERT INTO dbo.tbl" + tableName;

        // Build the second section of the query, where the column names are used [e.g. (TableID, TableKey, TableField1, TableField2)]
        // Build the third section of the query, with the actual values, in the form of: VALUES (1, A, "FieldData", true)
        String fieldNames = ""; // e.g. (TableID, TableKey, TableField1, TableField2)
        String values = ""; // e.g. (1, A, "FieldData", true)
        Iterator<JSONObject> keys = fields.iterator();
        while(keys.hasNext()) {
            JSONObject keyJSON = keys.next();
            Iterator<String> keyNames = keyJSON.keySet().iterator();
            String valueKey = (String) keyNames.next(); // Get the first (and only) field name from the JSON object

            Object value = getFromJSONArray(fields, valueKey);

            // Add the key to the list of fields (column titles in the database).
            fieldNames += valueKey + ", ";

            // Add the placeholder for the value, including a trailing comma.
            values += "?, ";
        }

        // Trim the last ", " from the strings
        fieldNames = fieldNames.substring(0, fieldNames.length() - 2);
        values = values.substring(0, values.length() - 2);

        // Put it all together to form the query
        query += (" (" + fieldNames + ") VALUES (" + values + ")");

        return query;
    }

    public static Object getFromJSONArray(JSONArray arr, String key) {
        // Get the key value from the field array.
        try {
            Iterator<JSONObject> iterator = arr.iterator();
            while (iterator.hasNext()) {
                JSONObject valueJSON = iterator.next();
                Object value = valueJSON.get(key);
                if (value != null) {
                    return value;
                }
            }
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}