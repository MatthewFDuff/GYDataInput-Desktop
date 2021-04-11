package gydatainput.models.specialist;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SpecAssoc extends Table {

    public SpecAssoc() {
    }

    public SpecAssoc(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
