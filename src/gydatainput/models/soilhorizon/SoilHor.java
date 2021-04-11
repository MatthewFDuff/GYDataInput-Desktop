package gydatainput.models.soilhorizon;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SoilHor extends Table {

    public SoilHor() {
    }

    public SoilHor(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
