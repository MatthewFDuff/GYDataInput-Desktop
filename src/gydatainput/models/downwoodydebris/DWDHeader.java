package gydatainput.models.downwoodydebris;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class DWDHeader extends Table {

    private ArrayList<DWDLine> dwdLine;
    private ArrayList<DWD> dwd;

    public DWDHeader() {}

    @Override
    public void fetchData() throws SQLException {
        this.dwdLine = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblDWDLine", DWDLine.class);
        this.dwd = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblDWD", DWD.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // DWD Line
        if (!dwdLine.isEmpty()) {
            JSONArray dwdLineJSON = new JSONArray();
            dwdLine.forEach((n) -> {
                dwdLineJSON.add(n.getJSON());
            });
            json.put("DWDLine", dwdLineJSON);
        }

        // DWD
        if (!dwd.isEmpty()) {
            JSONArray dwdJSON = new JSONArray();
            dwd.forEach((n) -> {
                dwdJSON.add(n.getJSON());
            });
            json.put("DWD", dwdJSON);
        }

        return json;
    }
}
