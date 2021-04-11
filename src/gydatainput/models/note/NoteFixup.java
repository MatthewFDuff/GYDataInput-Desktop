package gydatainput.models.note;

import gydatainput.models.Table;
import org.json.simple.JSONObject;

public class NoteFixup extends Table {

    public NoteFixup() {
    }

    public NoteFixup(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
