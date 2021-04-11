package gydatainput.models.standinformation;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class StandInfoHeader extends Table {

    public StandInfoHeader() {
    }

    public StandInfoHeader(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
