package gydatainput.models.specialist;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class SpecGYHeader extends Table {

    public SpecGYHeader() {
    }

    public SpecGYHeader(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
