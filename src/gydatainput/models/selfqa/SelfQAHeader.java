package gydatainput.models.selfqa;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelfQAHeader extends Table {

    private ArrayList<SelfQAHt> selfQAHt;
    private ArrayList<SelfQATree> selfQATree;

    public SelfQAHeader() {
    }

    public SelfQAHeader(JSONObject json, boolean isImport) {
        super(json, isImport);

        // Self QA Ht
        this.selfQAHt = getArrayFromJSON(json, "tblSelfQAHt", SelfQAHt.class);

        // Self QA Tree
        this.selfQATree = getArrayFromJSON(json, "tblSelfQATree", SelfQATree.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(selfQAHt);
        uploadArray(selfQATree);
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
