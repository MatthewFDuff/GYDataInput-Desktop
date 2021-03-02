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
        JSONObject json = this.getFields();

        if (!stkgLine.isEmpty()) {
            JSONArray stkgLineJSON = new JSONArray();
            stkgLine.forEach((n) -> {
                stkgLineJSON.add(n.getJSON());
            });
            json.put("StkgLine", stkgLineJSON);
        }

        return json;
    }
}
