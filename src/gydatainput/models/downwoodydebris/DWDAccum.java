package gydatainput.models.downwoodydebris;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class DWDAccum extends Table {

    public DWDAccum() {
    }

    public DWDAccum(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
