package gydatainput.models.stocking;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class StkgHeader extends Table {

    private ArrayList<StkgLine> stkgLine;

    public StkgHeader()  {
    }

    @Override
    public void fetchData() throws SQLException {
        this.stkgLine = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblStkgLine", StkgLine.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // StkgLine
        JSONArray stkgLineJSON = getChildFieldArrayWithJSON(stkgLine);
        if(stkgLineJSON != null) {
            json.put("tblStkgLine", stkgLineJSON);
        }

        return json;
    }
}
