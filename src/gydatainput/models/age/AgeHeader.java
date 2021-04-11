package gydatainput.models.age;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class AgeHeader extends Table {
    private ArrayList<AgeTree> ageTree;

    public AgeHeader() {
    }

    public AgeHeader(JSONObject json, boolean isImport) {
        super(json, isImport);

        // Age Tree
        this.ageTree = getArrayFromJSON(json, "tblAgeTree", AgeTree.class);
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
