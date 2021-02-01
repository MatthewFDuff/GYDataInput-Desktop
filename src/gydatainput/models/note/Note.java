package gydatainput.models.note;

public class Note {
    private int noteKey;
    private int plotKey;
    private int noteTypeCode;
    private String note;

    public int getNoteKey() {
        return noteKey;
    }

    public void setNoteKey(int noteKey) {
        this.noteKey = noteKey;
    }

    public int getPlotKey() {
        return plotKey;
    }

    public void setPlotKey(int plotKey) {
        this.plotKey = plotKey;
    }

    public int getNoteTypeCode() {
        return noteTypeCode;
    }

    public void setNoteTypeCode(int noteTypeCode) {
        this.noteTypeCode = noteTypeCode;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Note(int noteKey, int noteTypeCode, String note) {
        this.noteKey = noteKey;
        this.noteTypeCode = noteTypeCode;
        this.note = note;
    }
}
