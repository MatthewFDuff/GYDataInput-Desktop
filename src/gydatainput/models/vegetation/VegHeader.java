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
        JSONObject json = this.getFields();

        if (!vegVType.isEmpty()) {
            JSONArray vegVTypeJSON = new JSONArray();
            vegVType.forEach((n) -> {
                vegVTypeJSON.add(n.getFields());
            });
            json.put("VegVType", vegVTypeJSON);
        }

        if (!vegPlot.isEmpty()) {
            JSONArray vegPlotJSON = new JSONArray();
            vegPlot.forEach((n) -> {
                vegPlotJSON.add(n.getJSON());
            });
            json.put("VegPlot", vegPlotJSON);
        }

        return json;
    }
}
