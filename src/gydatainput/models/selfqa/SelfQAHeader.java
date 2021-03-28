package gydatainput.models.selfqa;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelfQAHeader extends Table {

    private ArrayList<SelfQAHt> selfQAHt;
    private ArrayList<SelfQATree> selfQATree;

    public SelfQAHeader() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.selfQAHt = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSelfQAHt", SelfQAHt.class);
        this.selfQATree = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSelfQATree", SelfQATree.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // SelfQAHt
        JSONArray selfQAHtJSON = getChildFieldArray(selfQAHt);
        if(selfQAHtJSON != null) {
            json.put("tblSelfQAHt", selfQAHtJSON);
        }

        // SelfQATree
        JSONArray selfQATreeJSON = getChildFieldArrayWithJSON(selfQATree);
        if(selfQATreeJSON != null) {
            json.put("tblSelfQATree", selfQATreeJSON);
        }

        return json;
    }
}
