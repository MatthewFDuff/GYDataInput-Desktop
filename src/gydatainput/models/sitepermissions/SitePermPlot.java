package gydatainput.models.sitepermissions;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SitePermPlot extends Table {

    public SitePermPlot() {
    }

    public SitePermPlot(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
