package gydatainput.models.height;

import gydatainput.database.DatabaseHelper;

public class HtHeader {
    private int htHeaderKey;
    private int visitKey;
    private String msrDate;

    private Ht[] hts;

    public Ht[] getHts() {
        return hts;
    }

    public void setHts(Ht[] hts) {
        this.hts = hts;
    }

    // CONSTRUCTOR
    public HtHeader(int htHeaderKey, int visitKey, String msrDate) {
        this.htHeaderKey = htHeaderKey;
        this.visitKey = visitKey;
        this.msrDate = msrDate;

        // TODO get heights as an array in here? They're already being pulled for each tree msr. Or we can link each height to the header.
    }

    public int getHtHeaderKey() {
        return htHeaderKey;
    }

    public void setHtHeaderKey(int htHeaderKey) {
        this.htHeaderKey = htHeaderKey;
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
}
