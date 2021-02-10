package gydatainput.models;

import org.json.simple.JSONObject;

/**
 * Table represents a table from the Growth & Yield database.
 */
public class Table {
    private JSONObject fields;

    /**
     * Construct a table with a JSONObject containing all columns of a database table.
     * @param fields each value contained in a column
     */
    public Table(JSONObject fields) {
        this.fields = fields;
    }

    public JSONObject getFields() {
        return fields;
    }
}
