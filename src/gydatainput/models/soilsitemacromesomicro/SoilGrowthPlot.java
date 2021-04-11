package gydatainput.models.soilsitemacromesomicro;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SoilGrowthPlot extends Table {

    public SoilGrowthPlot() {
    }

    public SoilGrowthPlot(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
