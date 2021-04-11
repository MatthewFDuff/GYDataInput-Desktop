package gydatainput.models.soilsitemacromesomicro;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SoilPlot extends Table {

    public SoilPlot() {
    }

    public SoilPlot(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
