package gydatainput.models.stocking;

public class StkgHeader {
    private int stkgHeaderKey;
    private int visitKey;
    private String msrDate;

    private StkgLine stkgLine;

    // StkgHeader Constructor
    public StkgHeader(int stkgHeaderKey, int visitKey, String msrDate) {
        this.stkgHeaderKey = stkgHeaderKey;
        this.visitKey = visitKey;
        this.msrDate = msrDate;
    }

    public int getStkgHeaderKey() {
        return stkgHeaderKey;
    }

    public void setStkgHeaderKey(int stkgHeaderKey) {
        this.stkgHeaderKey = stkgHeaderKey;
    }

    public int getVisitKey() {
        return visitKey;
    }

    public void setVisitKey(int visitKey) {
        this.visitKey = visitKey;
    }

    public String getMsrDate() {
        return msrDate;
    }

    public void setMsrDate(String msrDate) {
        this.msrDate = msrDate;
    }

    public StkgLine getStkgLine() {
        return stkgLine;
    }

    public void setStkgLine(StkgLine stkgLine) {
        this.stkgLine = stkgLine;
    }
}
