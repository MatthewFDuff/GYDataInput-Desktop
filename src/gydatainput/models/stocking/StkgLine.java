package gydatainput.models.stocking;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class StkgLine extends Table {

    private ArrayList<Stkg> stkg;

    public StkgLine() {
    }

    public StkgLine(JSONObject json, boolean isImport) {
        super(json, isImport);

        this.stkg = getArrayFromJSON(json, "tblStkg", Stkg.class);
    }

    @Override
    public void fetchData() throws SQLException {
        this.stkg = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblStkg", Stkg.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Stkg
        JSONArray stkgJSON = getChildFieldArray(stkg);
        if(stkgJSON != null) {
            json.put("tblStkg", stkgJSON);
        }

        return json;
    }
}
