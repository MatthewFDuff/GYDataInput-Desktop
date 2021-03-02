package gydatainput.models.age;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class AgeTree extends Table {
    private ArrayList<AgeSample> ageSample;

    /**
     *
     *
     * @param fields each value contained in a column
     */
    public AgeTree() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.ageSample = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblAgeSample", AgeSample.class);
    }

    public JSONObject getJSON() {
        JSONObject json = this.getFields();

        // Age Sample
        if (!ageSample.isEmpty()) {
            JSONArray ageSampleJSON = new JSONArray();
            ageSample.forEach((n) -> {
                ageSampleJSON.add(n.getFields());
            });
            json.put("MortTreeMsr", ageSampleJSON);
        }

        return json;
    }
}
