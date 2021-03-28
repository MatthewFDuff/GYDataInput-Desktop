package gydatainput.models.vegetation;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class VegHeader extends Table {
    private ArrayList<VegVType> vegVType;
    private ArrayList<VegPlot> vegPlot;

    public VegHeader() {

    }

    @Override
    public void fetchData() throws SQLException {
        this.vegVType = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblVegVType", VegVType.class);
        this.vegPlot = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblVegPlot", VegPlot.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // VegVType
        JSONArray vegVTypeJSON = getChildFieldArray(vegVType);
        if(vegVTypeJSON != null) {
            json.put("tblVegVType", vegVTypeJSON);
        }

        // VegPlot
        JSONArray vegPlotJSON = getChildFieldArrayWithJSON(vegPlot);
        if(vegPlotJSON != null) {
            json.put("tblVegPlot", vegPlotJSON);
        }

        return json;
    }
}
