package gydatainput.models.downwoodydebris;

import gydatainput.database.DatabaseHelper;

public class DWDLine {
    private int dwdLineKey;
    private int dwdHeaderKey;
    private int lineNum;
    private int azi;
    private double length;
    private boolean stumpPres;

    private DWDIntersect[] dwdIntersections;
    private DWDStump[] dwdStumps;
    private DWDAccum[] dwdAccums;

    public DWDIntersect[] getDwdIntersections() {
        return dwdIntersections;
    }

    public void setDwdIntersections(DWDIntersect[] dwdIntersections) {
        this.dwdIntersections = dwdIntersections;
    }

    public DWDStump[] getDwdStumps() {
        return dwdStumps;
    }

    public void setDwdStumps(DWDStump[] dwdStumps) {
        this.dwdStumps = dwdStumps;
    }

    public DWDAccum[] getDwdAccums() {
        return dwdAccums;
    }

    public void setDwdAccums(DWDAccum[] dwdAccums) {
        this.dwdAccums = dwdAccums;
    }

    public int getDwdLineKey() {
        return dwdLineKey;
    }

    public void setDwdLineKey(int dwdLineKey) {
        this.dwdLineKey = dwdLineKey;
    }

    public int getDwdHeaderKey() {
        return dwdHeaderKey;
    }

    public void setDwdHeaderKey(int dwdHeaderKey) {
        this.dwdHeaderKey = dwdHeaderKey;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getAzi() {
        return azi;
    }

    public void setAzi(int azi) {
        this.azi = azi;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean isStumpPres() {
        return stumpPres;
    }

    public void setStumpPres(boolean stumpPres) {
        this.stumpPres = stumpPres;
    }

    // CONSTRUCTOR
    public DWDLine(int dwdLineKey, int dwdHeaderKey, int lineNum, int azi, double length, boolean stumpPres) {
        this.dwdLineKey = dwdLineKey;
        this.dwdHeaderKey = dwdHeaderKey;
        this.lineNum = lineNum;
        this.azi = azi;
        this.length = length;
        this.stumpPres = stumpPres;

        this.dwdIntersections = DatabaseHelper.getDWDIntersections(dwdLineKey);
        this.dwdAccums = DatabaseHelper.getDWDAccum(dwdLineKey);
        this.dwdStumps = DatabaseHelper.getDWDStumps(dwdLineKey);
    }
}
