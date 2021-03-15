package gydatainput.models.downwoodydebris;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class DWDLine extends Table {
    private ArrayList<DWDIntersect> dwdIntersect;
    private ArrayList<DWDStump> dwdStump;
    private ArrayList<DWDAccum> dwdAccum;

    public DWDLine() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.dwdIntersect = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblDWDIntersect", DWDIntersect.class);
        this.dwdStump = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblDWDStump", DWDStump.class);
        this.dwdAccum = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblDWDAccum", DWDAccum.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // DWD Intersect
        if (!dwdIntersect.isEmpty()) {
            JSONArray dwdIntersectJSON = new JSONArray();
            dwdIntersect.forEach((n) -> {
                dwdIntersectJSON.add(n.getFields());
            });
            json.put("DWDIntersect", dwdIntersectJSON);
        }

        // DWD Stump
        if (!dwdStump.isEmpty()) {
            JSONArray dwdStumpJSON = new JSONArray();
            dwdStump.forEach((n) -> {
                dwdStumpJSON.add(n.getFields());
            });
            json.put("DWDStump", dwdStumpJSON);
        }

        // DWD Accum
        if (!dwdAccum.isEmpty()) {
            JSONArray dwdAccumJSON = new JSONArray();
            dwdAccum.forEach((n) -> {
                dwdAccumJSON.add(n.getFields());
            });
            json.put("DWDAccum", dwdAccumJSON);
        }

        return json;
    }
}
