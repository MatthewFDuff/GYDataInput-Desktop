package gydatainput.models.downwoodydebris;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class DWD extends Table {

    private ArrayList<DWDIntersect> dwdIntersect;

    public DWD() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.dwdIntersect = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblDWDIntersect", DWDIntersect.class);
    }

    public Object getJSON() {
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

        return json;
    }
}
