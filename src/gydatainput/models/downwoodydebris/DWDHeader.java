package gydatainput.models.downwoodydebris;

import gydatainput.database.DatabaseHelper;

public class DWDHeader {
    private int dwdHeaderKey;
    private int visitKey;
    private String msrDate;

    private DWDLine[] dwdLines;
    private DWD[] dwds;

    public DWDLine[] getDwdLines() {
        return dwdLines;
    }

    public void setDwdLines(DWDLine[] dwdLines) {
        this.dwdLines = dwdLines;
    }

    public DWD[] getDwds() {
        return dwds;
    }

    public void setDwds(DWD[] dwds) {
        this.dwds = dwds;
    }

    // CONSTRUCTOR
    public DWDHeader(int dwdHeaderKey, int visitKey, String msrDate) {
        this.dwdHeaderKey = dwdHeaderKey;
        this.visitKey = visitKey;
        this.msrDate = msrDate;

        this.dwdLines = DatabaseHelper.getDWDLines(dwdHeaderKey);
        this.dwds = DatabaseHelper.getDWD(dwdHeaderKey);
    }

    public int getDwdHeaderKey() {
        return dwdHeaderKey;
    }

    public void setDwdHeaderKey(int dwdHeaderKey) {
        this.dwdHeaderKey = dwdHeaderKey;
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
