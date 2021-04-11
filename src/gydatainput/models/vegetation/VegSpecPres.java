package gydatainput.models.vegetation;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class VegSpecPres extends Table {

    public VegSpecPres() {
    }

    public VegSpecPres(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
