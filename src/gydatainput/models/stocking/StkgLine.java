package gydatainput.models.stocking;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class StkgLine extends Table {

    private ArrayList<Stkg> stkg;

    public StkgLine() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.stkg = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblStkg", Stkg.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Stkg
        if (!stkg.isEmpty()) {
            JSONArray stkgJSON = new JSONArray();
            stkg.forEach((n) -> {
                stkgJSON.add(n.getFields());
            });
            json.put("Stkg", stkgJSON);
        }

        return json;
    }
}
