package gydatainput.models.tree;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class TreeHeader extends Table {

    private ArrayList<TreeGrowthPlot> treeGrowthPlot;

    public TreeHeader() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.treeGrowthPlot = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblTreeGrowthPlot", TreeGrowthPlot.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // TreeGrowthPlot
        JSONArray treeGrowthPlotJSON = getChildFieldArrayWithJSON(treeGrowthPlot);
        if(treeGrowthPlotJSON != null) {
            json.put("tblTreeGrowthPlot", treeGrowthPlotJSON);
        }

        return json;
    }
}
