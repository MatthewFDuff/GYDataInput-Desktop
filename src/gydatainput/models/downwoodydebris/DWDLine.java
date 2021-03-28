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
        JSONArray dwdIntersectJSON = getChildFieldArray(dwdIntersect);
        if(dwdIntersectJSON != null) {
            json.put("tblDWDIntersect", dwdIntersectJSON);
        }

        // DWD Stump
        JSONArray dwdStumpJSON = getChildFieldArray(dwdStump);
        if(dwdStumpJSON != null) {
            json.put("tblDWDStump", dwdStumpJSON);
        }

        // DWD Accum
        JSONArray dwdAccumJSON = getChildFieldArray(dwdAccum);
        if(dwdAccumJSON != null) {
            json.put("tblDWDAccum", dwdAccumJSON);
        }

        return json;
    }
}
