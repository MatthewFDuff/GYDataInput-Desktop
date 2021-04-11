package gydatainput.models.soilsitetemporal;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SoilForestFloor extends Table {

    public SoilForestFloor() {
    }

    public SoilForestFloor(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
