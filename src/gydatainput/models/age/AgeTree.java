package gydatainput.models.age;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class AgeTree extends Table {
    private ArrayList<AgeSample> ageSample;

    /**
     *
     *
     */
    public AgeTree() {
    }

    public AgeTree(JSONObject json, boolean isImport) {
        super(json, isImport);

        // Age Sample
        this.ageSample = getArrayFromJSON(json, "tblAgeSample", AgeSample.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(ageSample);
    }

    @Override
    public void fetchData() throws SQLException {
        this.ageSample = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblAgeSample", AgeSample.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Age Sample
        JSONArray ageSampleJSON = getChildFieldArray(ageSample);
        if (ageSampleJSON != null) {
            json.put("tblAgeSample", ageSampleJSON);
        }

        return json;
    }
}
