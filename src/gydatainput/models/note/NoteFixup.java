package gydatainput.models.note;

public class NoteFixup {
    private int noteFixupKey;
    private int elementCode;
    private int recordedYear;

    public int getNoteFixupKey() {
        return noteFixupKey;
    }

    public void setNoteFixupKey(int noteFixupKey) {
        this.noteFixupKey = noteFixupKey;
    }

    public int getElementCode() {
        return elementCode;
    }

    public void setElementCode(int elementCode) {
        this.elementCode = elementCode;
    }

    public int getRecordedYear() {
        return recordedYear;
    }

    public void setRecordedYear(int recordedYear) {
        this.recordedYear = recordedYear;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    private String note;
    private boolean fixed;

    public NoteFixup(int noteFixupKey, int elementCode, int recordedYear, String note, boolean fixed) {
        this.noteFixupKey = noteFixupKey;
        this.elementCode = elementCode;
        this.recordedYear = recordedYear;
        this.note = note;
        this.fixed = fixed;
    }
}
