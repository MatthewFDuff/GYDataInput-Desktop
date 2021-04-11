package gydatainput.models.plotmapping;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class PlotMapHeader extends Table {
    public PlotMapHeader() {
    }

    public PlotMapHeader(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
