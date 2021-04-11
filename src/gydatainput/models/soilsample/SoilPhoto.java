package gydatainput.models.soilsample;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

/**
 * Soil Photo
 *
 */
public class SoilPhoto extends Table {

    public SoilPhoto() {
    }

    public SoilPhoto(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
