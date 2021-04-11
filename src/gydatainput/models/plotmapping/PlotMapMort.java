package gydatainput.models.plotmapping;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class PlotMapMort extends Table {

    public PlotMapMort() {
    }

    public PlotMapMort(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
