package gydatainput.models.tree;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class TreeHeader extends Table {

    private ArrayList<TreeGrowthPlot> treeGrowthPlot;

    public TreeHeader() {
    }

    public TreeHeader(JSONObject json, boolean isImport) {
        super(json, isImport);

        // TreeGrowthPlot
        this.treeGrowthPlot = getArrayFromJSON(json, "tblTreeGrowthPlot", TreeGrowthPlot.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(treeGrowthPlot);
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
