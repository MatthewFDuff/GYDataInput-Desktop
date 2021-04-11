package gydatainput.models.mortality;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;

public class MortTreeMsr extends Table {

    private MortTree mortTree;

    public MortTreeMsr() {
    }

    public MortTreeMsr(JSONObject json, boolean isImport) {
        super(json, isImport);

        // MortTree
        this.mortTree = getObjectFromJSON(json, "tblMortTree", MortTree.class);
    }

    @Override
    public void fetchData() throws SQLException {
        this.mortTree = DatabaseHelper.getData(getKey(), getKeyName(), "tblMortTree", MortTree.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Mort Tree
        if (mortTree != null) {
            json.put("tblMortTree", mortTree.getJSON());
        }

        return json;
    }
}
