package gydatainput.models.location;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class PlotAccess extends Table {

    public PlotAccess() {
    }

    public PlotAccess(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
