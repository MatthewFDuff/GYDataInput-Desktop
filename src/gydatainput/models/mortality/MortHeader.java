package gydatainput.models.mortality;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class MortHeader extends Table {
    private ArrayList<MortTreeMsr> mortTreeMsr;

    public MortHeader() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.mortTreeMsr = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblMortTreeMsr", MortTreeMsr.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Mort Tree Msr
        JSONArray mortTreeMsrJSON = getChildFieldArrayWithJSON(mortTreeMsr);
        if(mortTreeMsrJSON != null) {
            json.put("tblMortTreeMsr", mortTreeMsrJSON);
        }

        return json;
    }
}
