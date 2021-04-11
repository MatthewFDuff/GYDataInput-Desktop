package gydatainput.models.soilsample;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SoilDepMode extends Table {

    public SoilDepMode() {
    }

    public SoilDepMode(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
