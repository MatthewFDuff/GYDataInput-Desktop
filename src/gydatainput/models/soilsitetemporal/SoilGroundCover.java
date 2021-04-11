package gydatainput.models.soilsitetemporal;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SoilGroundCover extends Table {

    public SoilGroundCover() {
    }

    public SoilGroundCover(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
