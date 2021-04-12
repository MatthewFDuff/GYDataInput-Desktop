package gydatainput.models.downwoodydebris;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class DWDHeader extends Table {

    private ArrayList<DWDLine> dwdLine;
    private ArrayList<DWD> dwd;

    public DWDHeader() {}

    public DWDHeader(JSONObject json, boolean isImport) {
        super(json, isImport);

        // DWD Line
        this.dwdLine = getArrayFromJSON(json, "tblDWDLine", DWDLine.class);

        // DWD
        this.dwd = getArrayFromJSON(json, "tblDWD", DWD.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(dwdLine);
        uploadArray(dwd);
    }

    @Override
    public void fetchData() throws SQLException {
        this.dwdLine = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblDWDLine", DWDLine.class);
        this.dwd = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblDWD", DWD.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // DWD Line
        JSONArray dwdLineJSON = getChildFieldArrayWithJSON(dwdLine);
        if(dwdLineJSON != null) {
            json.put("tblDWDLine", dwdLineJSON);
        }

        // DWD
        JSONArray dwdJSON = getChildFieldArrayWithJSON(dwd);
        if(dwdJSON != null) {
            json.put("tblDWD", dwdJSON);
        }

        return json;
    }
}
