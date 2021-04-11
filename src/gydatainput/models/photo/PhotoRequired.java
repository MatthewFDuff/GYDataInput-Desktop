package gydatainput.models.photo;

import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONObject;

public class PhotoRequired extends Table {

    public PhotoRequired() {
    }

    public PhotoRequired(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
