package gydatainput.models.tree;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class TreeGrowthPlot extends Table {

    private ArrayList<TreeMsr> treeMsr;

    public TreeGrowthPlot(){
    }

    public TreeGrowthPlot(JSONObject json, boolean isImport) {
        super(json, isImport);

        // TreeMsr
        this.treeMsr = getArrayFromJSON(json, "tblTreeMsr", TreeMsr.class);
    }

    @Override
    public void fetchData() throws SQLException {
        this.treeMsr = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblTreeMsr", TreeMsr.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // TreeMsr
        JSONArray treeMsrJSON = getChildFieldArrayWithJSON(treeMsr);
        if(treeMsrJSON != null) {
            json.put("tblTreeMsr", treeMsrJSON);
        }

        return json;
    }
}
