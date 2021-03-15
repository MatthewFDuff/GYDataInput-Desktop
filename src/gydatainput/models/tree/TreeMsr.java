package gydatainput.models.tree;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.cavity.TreeCav;
import gydatainput.models.deformity.TreeDefm;
import gydatainput.models.height.Ht;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class TreeMsr extends Table {
    private TreeMissed treeMissed;
    private ArrayList<TreeDefm> treeDefm;
    private ArrayList<TreeCav> treeCav;
    private Ht ht;

    public TreeMsr() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.treeMissed = DatabaseHelper.getData(getKey(), getKeyName(), "tblTreeMissed", TreeMissed.class);
        this.treeDefm = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblTreeDefm", TreeDefm.class);
        this.treeCav = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblTreeCav", TreeCav.class);
        this.ht = DatabaseHelper.getData(getKey(), getKeyName(), "tblHt", Ht.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        if (treeMissed != null) {
            json.put("TreeMissed", treeMissed.getFields());
        }

        if (!treeDefm.isEmpty()) {
            JSONArray treeDefmJSON = new JSONArray();
            treeDefm.forEach((n) -> {
                treeDefmJSON.add(n.getFields());
            });
            json.put("TreeDefm", treeDefmJSON);
        }

        if (!treeCav.isEmpty()) {
            JSONArray treeCavJSON = new JSONArray();
            treeCav.forEach((n) -> {
                treeCavJSON.add(n.getFields());
            });
            json.put("TreeCav", treeCavJSON);
        }

        if (ht != null) {
            json.put("Ht", ht.getFields());
        }

        return json;
    }
}
