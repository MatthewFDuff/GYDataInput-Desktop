package gydatainput.models;

import gydatainput.database.DatabaseHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Table represents a table from the Growth & Yield database.
 */
public class Table {
    private JSONObject fields;
    private int key;
    private String keyName;

    public Table() {}

    /**
     * Construct a table with a JSONObject containing all columns of a database table.
     * @param fields each value contained in a column
     */
    public Table(JSONObject fields) {
        this.fields = fields;

        if (fields != null) {
            // Get all column headers (keys)
            Set<String> keys = fields.keySet();
            // Create an iterator to move through the set of keys.
            Iterator<String> iterator = keys.iterator();
            // The first key in the set is the primary key for the table.
            this.keyName = iterator.next();
            // Use the first key in the set to get the value of the key.
            this.key = (int) fields.get(this.keyName);

//            System.out.println(this.getClass() + ": " + getKeyName() + ": " + getKey());
        }
    }
    public void fetchData() throws SQLException {
        return;
    }

    public JSONObject getFields() {
        return fields;
    }

    public void setFields(JSONObject fields) { this.fields = fields; }

    public int getKey() {
        return key;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}
