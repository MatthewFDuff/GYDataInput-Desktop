package gydatainput.models.tree;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.cavity.TreeCav;
import gydatainput.models.deformity.TreeDefm;
import gydatainput.models.height.Ht;
import gydatainput.models.plotpackage.Visit;
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

    public TreeMsr(JSONObject json, boolean isImport) {
        super(json, isImport);

        // TreeMissed
        this.treeMissed = getObjectFromJSON(json, "tblTreeMissed", TreeMissed.class);

        // TreeDefm
        this.treeDefm = getArrayFromJSON(json, "tblTreeDefm", TreeDefm.class);

        // TreeCav
        this.treeCav = getArrayFromJSON(json, "tblTreeCav", TreeCav.class);

        // Ht
        this.ht = getObjectFromJSON(json, "tblHt", Ht.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadObject(treeMissed);
        uploadArray(treeDefm);
        uploadArray(treeCav);
        // Ht does not need to be uploaded because it's already uploaded in another class // TODO which class? Ht header?.
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

        // TreeMissed
        if (treeMissed != null) {
            json.put("tblTreeMissed", treeMissed.getJSON());
        }

        // TreeDefm
        JSONArray treeDefmJSON = getChildFieldArray(treeDefm);
        if(treeDefmJSON != null) {
            json.put("tblTreeDefm", treeDefmJSON);
        }

        // TreeCav
        JSONArray treeCavJSON = getChildFieldArray(treeCav);
        if(treeCavJSON != null) {
            json.put("tblTreeCav", treeCavJSON);
        }

        // Ht
        if (ht != null) {
            json.put("tblHt", ht.getJSON());
        }

        return json;
    }
}
