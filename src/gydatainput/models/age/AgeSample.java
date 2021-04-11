package gydatainput.models.age;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class AgeSample extends Table {

    public AgeSample() {
    }

    public AgeSample(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
