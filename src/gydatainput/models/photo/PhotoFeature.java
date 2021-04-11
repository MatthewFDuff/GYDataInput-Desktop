package gydatainput.models.photo;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class PhotoFeature extends Table {

    public PhotoFeature() {
    }

    public PhotoFeature(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
