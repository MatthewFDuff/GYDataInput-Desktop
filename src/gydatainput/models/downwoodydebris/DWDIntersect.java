package gydatainput.models.downwoodydebris;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class DWDIntersect extends Table {
    public DWDIntersect() {
    }

    public DWDIntersect(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
