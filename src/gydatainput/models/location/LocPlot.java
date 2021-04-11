package gydatainput.models.location;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class LocPlot extends Table {

    public LocPlot() {
    }

    public LocPlot(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
