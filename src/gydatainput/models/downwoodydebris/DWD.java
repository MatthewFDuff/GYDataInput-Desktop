package gydatainput.models.downwoodydebris;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class DWD extends Table {

    private ArrayList<DWDIntersect> dwdIntersect;

    public DWD() {
    }

    public DWD(JSONObject json, boolean isImport) {
        super(json, isImport);

        // DWD Intersect
        this.dwdIntersect = getArrayFromJSON(json, "tblDWDIntersect", DWDIntersect.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(dwdIntersect);
    }

    @Override
    public void fetchData() throws SQLException {
        this.dwdIntersect = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblDWDIntersect", DWDIntersect.class);
    }

    public Object getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // DWD Intersect
        JSONArray dwdIntersectJSON = getChildFieldArray(dwdIntersect);

        if(dwdIntersectJSON != null) {
            json.put("tblDWDIntersect", dwdIntersectJSON);
        }

        return json;
    }
}
