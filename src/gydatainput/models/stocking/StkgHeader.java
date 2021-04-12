package gydatainput.models.stocking;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class StkgHeader extends Table {

    private ArrayList<StkgLine> stkgLine;

    public StkgHeader()  {
    }

    public StkgHeader(JSONObject json, boolean isImport) {
        super(json, isImport);

        // StkgLine
        this.stkgLine = getArrayFromJSON(json, "tblStkgLine", StkgLine.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(stkgLine);
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
