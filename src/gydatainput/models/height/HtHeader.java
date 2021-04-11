package gydatainput.models.height;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class HtHeader extends Table {
    private ArrayList<Ht> ht;

    public HtHeader() {
    }

    public HtHeader(JSONObject json, boolean isImport) {
        super(json, isImport);

        // Ht
        this.ht = getArrayFromJSON(json, "tblHt", Ht.class);
    }

    @Override
    public void fetchData() throws SQLException {
        this.ht = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblHt", Ht.class);

    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Ht TODO This may not be necessary? Ht is already in TreeMsr.
        JSONArray htJSON = getChildFieldArray(ht);
        if(htJSON != null) {
            json.put("tblHt", htJSON);
        }

        return json;
    }
}
