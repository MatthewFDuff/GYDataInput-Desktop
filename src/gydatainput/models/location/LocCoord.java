package gydatainput.models.location;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class LocCoord extends Table {

    public LocCoord() {
    }

    public LocCoord(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
