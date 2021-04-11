package gydatainput.models.standinformation;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class StandInfoTreat extends Table {

    public StandInfoTreat() {
    }

    public StandInfoTreat(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
