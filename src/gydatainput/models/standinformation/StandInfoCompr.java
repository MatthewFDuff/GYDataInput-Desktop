package gydatainput.models.standinformation;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class StandInfoCompr extends Table {

    public StandInfoCompr() {
    }

    public StandInfoCompr(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
