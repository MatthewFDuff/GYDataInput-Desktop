package gydatainput.models.selfqa;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelfQATree extends Table {
    private ArrayList<SelfQADeform> selfQADeform;

    public SelfQATree() {
    }

    public SelfQATree(JSONObject json, boolean isImport) {
        super(json, isImport);

        // SelfQADeform
        this.selfQADeform = getArrayFromJSON(json, "tblSelfQADeform", SelfQADeform.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(selfQADeform);
    }

    @Override
    public void fetchData() throws SQLException {
        this.selfQADeform = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSelfQADeform", SelfQADeform.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // SelfQADeform
        JSONArray selfQADeformJSON = getChildFieldArray(selfQADeform);
        if(selfQADeformJSON != null) {
            json.put("tblSelfQADeform", selfQADeformJSON);
        }

        return json;
    }
}
