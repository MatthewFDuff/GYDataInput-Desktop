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
        JSONObject json = this.getFields();

        if (!treeMsr.isEmpty()) {
            JSONArray treeMsrJSON = new JSONArray();
            treeMsr.forEach((n) -> {
                treeMsrJSON.add(n.getJSON());
            });
            json.put("TreeMsr", treeMsrJSON);
        }

        return json;
    }
}
