package gydatainput.models.deformity;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class TreeDefm extends Table {

    public TreeDefm() {
    }

    public TreeDefm(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
