package gydatainput.models.vegetation;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class VegCover extends Table {


    public VegCover() {
    }

    public VegCover(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
