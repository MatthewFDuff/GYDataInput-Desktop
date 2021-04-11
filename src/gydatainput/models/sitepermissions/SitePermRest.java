package gydatainput.models.sitepermissions;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SitePermRest extends Table {

    public SitePermRest() {
    }

    public SitePermRest(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
