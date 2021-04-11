package gydatainput.models.standinformation;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class StandInfoDistb extends Table {
    public StandInfoDistb() {
    }

    public StandInfoDistb(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
