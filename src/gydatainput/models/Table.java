package gydatainput.models;

import gydatainput.database.DatabaseHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Table represents a table from the Growth & Yield database.
 */
public class Table {
    private JSONArray fields;
    private int key;
    private String keyName;

    public Table() {}

    /**
     * Construct a table with a JSONObject containing all columns of a database table.
     * @param fields each value contained in a column
     */
    public Table(JSONArray fields) {
        this.fields = fields;

        // Create an iterator to go through each field.
        Iterator<JSONObject> iterator = fields.iterator();
        if(iterator.hasNext()) {
            JSONObject field = iterator.next(); // Get the next object.

            // Key Set
            Iterator<String> keys = (Iterator<String>) field.keySet().iterator();
            String primaryKey = keys.next();
            // The first key in the set is the primary key for the table.
            this.setKeyName(primaryKey);
            // Use the first key in the set to get the value of the key.
            this.key = (int) field.get(this.keyName);
        }
    }

    // Supply the name of a field and this will grab it from the JSONArray of fields if it exists.
    public Object getFromFields(String field) {
        try {
            Iterator<JSONObject> iterator = this.fields.iterator();
            while (iterator.hasNext()) {
                JSONObject fieldJSON = iterator.next();
                // Check if the current field matches the one being requested.
                if (fieldJSON.get(field) != null) {
                    return fieldJSON.get(field);
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void fetchData() throws SQLException {
        return;
    }

    protected JSONArray getFields() {
        return fields;
    }



    public static <T extends Table> JSONArray getChildFieldArray(ArrayList<T> children) {
        if (!children.isEmpty()) {
            JSONArray childrenJSON = new JSONArray();
            children.forEach((n) -> {
                JSONObject child = new JSONObject();
                child.put("fields", n.getFields());
                childrenJSON.add(child);
            });

            return childrenJSON;
        }
        // TODO Remove in prod.
        System.out.println("getChildFieldArray error, returned null");
        return null;
    }

    public static <T extends Table> JSONArray getChildFieldArrayWithJSON(ArrayList<T> children) {
        if (!children.isEmpty()) {
            JSONArray childrenJSON = new JSONArray();
            children.forEach((n) -> {
                childrenJSON.add(n.getJSON());
            });

            return childrenJSON;
        }
        // TODO Remove in prod.
        System.out.println("getChildFieldArray error, returned null");
        return null;
    }

    //public static <T extends Table> JSONObject

    public Object getJSON() {
        JSONObject fieldJSON = new JSONObject();
        fieldJSON.put("fields", this.getFields());
        return fieldJSON;
    }

    public void setFields(JSONArray fields) { this.fields = fields; }

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
