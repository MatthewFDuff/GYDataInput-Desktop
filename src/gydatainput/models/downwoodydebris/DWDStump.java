package gydatainput.models.downwoodydebris;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class DWDStump extends Table {
    public DWDStump() {
    }

    public DWDStump(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
