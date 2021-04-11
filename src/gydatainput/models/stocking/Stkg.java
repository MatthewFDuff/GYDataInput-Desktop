package gydatainput.models.stocking;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class Stkg extends Table {

    public Stkg() {
    }

    public Stkg(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
