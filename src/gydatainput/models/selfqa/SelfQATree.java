package gydatainput.models.selfqa;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class SelfQATree extends Table {
    private ArrayList<SelfQADeform> selfQADeform;

    public SelfQATree() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.selfQADeform = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSelfQADeform", SelfQADeform.class);
    }

    public JSONObject getJSON() {
        JSONObject json = this.getFields();

        if (!selfQADeform.isEmpty()) {
            JSONArray selfQADeformJSON = new JSONArray();
            selfQADeform.forEach((n) -> {
                selfQADeformJSON.add(n.getFields());
            });
            json.put("SelfQADeform", selfQADeformJSON);
        }

        return json;
    }
}
