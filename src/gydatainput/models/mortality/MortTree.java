package gydatainput.models.mortality;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class MortTree extends Table {

    public MortTree() {
    }

    public MortTree(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
