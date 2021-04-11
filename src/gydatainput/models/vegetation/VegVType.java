package gydatainput.models.vegetation;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class VegVType extends Table {

    public VegVType() {
    }

    public VegVType(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
