package gydatainput.models.note;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class NotePlot extends Table {

    public NotePlot() {
    }

    public NotePlot(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
