package gydatainput.models.vegetation;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class VegShrubSpec extends Table {

    public VegShrubSpec() {
    }

    public VegShrubSpec(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
