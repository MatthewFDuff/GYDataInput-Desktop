package gydatainput.models.height;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class Ht extends Table {
    public Ht() {
    }

    public Ht(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
