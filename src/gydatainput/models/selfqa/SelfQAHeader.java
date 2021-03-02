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

    public SelfQAHeader(){
    }

    @Override
    public void fetchData() throws SQLException {
        this.selfQAHt = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSelfQAHt", SelfQAHt.class);
        this.selfQATree = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSelfQATree", SelfQATree.class);
    }

    public JSONObject getJSON() {
        JSONObject json = this.getFields();

        if (!selfQAHt.isEmpty()) {
            JSONArray selfQAHtJSON = new JSONArray();
            selfQAHt.forEach((n) -> {
                selfQAHtJSON.add(n.getFields());
            });
            json.put("SelfQAHt", selfQAHtJSON);
        }

        if (!selfQATree.isEmpty()) {
            JSONArray selfQATreeJSON = new JSONArray();
            selfQATree.forEach((n) -> {
                selfQATreeJSON.add(n.getJSON());
            });
            json.put("SelfQATree", selfQATreeJSON);
        }

        return json;
    }
}
