package gydatainput.models.age;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class AgeHeader extends Table {
    private ArrayList<AgeTree> ageTree;

    public AgeHeader() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.ageTree = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblAgeTree", AgeTree.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Age Tree
        JSONArray ageTreeJSON = getChildFieldArrayWithJSON(ageTree);
        if (ageTree != null) {
            json.put("tblAgeTree", ageTreeJSON);
        }

        return json;
    }
}
