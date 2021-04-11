package gydatainput.models.tree;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class TreeMissed extends Table {

    public TreeMissed() {
    }

    public TreeMissed(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
