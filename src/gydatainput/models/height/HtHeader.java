package gydatainput.models.height;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class HtHeader extends Table {
    private ArrayList<Ht> ht;

    public HtHeader() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.ht = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblHt", Ht.class);

    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Ht TODO This may not be necessary? Ht is already in TreeMsr.
        if (!ht.isEmpty()) {
            JSONArray htJSON = new JSONArray();
            ht.forEach((n) -> {
                htJSON.add(n.getFields());
            });
            json.put("Ht", htJSON);
        }

        return json;
    }
}
