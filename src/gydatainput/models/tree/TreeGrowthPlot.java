package gydatainput.models.tree;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class TreeGrowthPlot extends Table {

    private ArrayList<TreeMsr> treeMsr;

    public TreeGrowthPlot(){
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
