package gydatainput.models.soilsitetemporal;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SoilEcosite extends Table {

    public SoilEcosite() {
    }

    public SoilEcosite(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
