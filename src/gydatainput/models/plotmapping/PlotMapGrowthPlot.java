package gydatainput.models.plotmapping;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.tree.Tree;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlotMapGrowthPlot extends Table {
    private ArrayList<Tree> tree;
    public PlotMapGrowthPlot() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.tree = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblTree", Tree.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Tree
        JSONArray treeJSON = getChildFieldArray(tree);
        if(treeJSON != null) {
            json.put("tblTree", treeJSON);
        }

        return json;
    }
}
