package gydatainput.models.mortality;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;

public class MortTreeMsr extends Table {

    private MortTree mortTree;

    public MortTreeMsr() throws SQLException {
    }

    @Override
    public void fetchData() throws SQLException {
        this.mortTree = DatabaseHelper.getData(getKey(), getKeyName(), "tblMortTree", MortTree.class);
    }

    public JSONObject getJSON() {
        JSONObject json = this.getFields();

        // Mort Tree
        if (mortTree != null) {
            json.put("MortTree", mortTree.getFields());
        }

        return json;
    }
}
