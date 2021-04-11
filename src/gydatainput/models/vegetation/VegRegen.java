package gydatainput.models.vegetation;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class VegRegen extends Table {

    public VegRegen() {
    }

    public VegRegen(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
