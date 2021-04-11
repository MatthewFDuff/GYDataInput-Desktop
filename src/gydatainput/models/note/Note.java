package gydatainput.models.note;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class Note extends Table {

    public Note() {
    }

    public Note(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
