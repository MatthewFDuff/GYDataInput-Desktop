package gydatainput.models.standinformation;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class StandInfoPlot extends Table {

    public StandInfoPlot() {
    }

    public StandInfoPlot(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
