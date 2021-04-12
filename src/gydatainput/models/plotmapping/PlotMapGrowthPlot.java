package gydatainput.models.plotmapping;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import gydatainput.models.tree.Tree;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlotMapGrowthPlot extends Table {
    private ArrayList<Tree> tree;

    public PlotMapGrowthPlot() {
    }

    public PlotMapGrowthPlot(JSONObject json, boolean isImport) {
        super(json, isImport);

        // Tree
        this.tree = getArrayFromJSON(json, "tblTree", Tree.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(tree);
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
