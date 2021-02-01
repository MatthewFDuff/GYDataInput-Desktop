package gydatainput.models.downwoodydebris;

public class DWDAccum {
    private int dwdAccumKey;
    private int dwdLineKey;
    private int accumNum;
    private double length;
    private double depth;
    private int decompClass5;
    private String swdOriginCode;
    private boolean burned;
    private int pctConifer;
    private int pctHdwd;

    public int getDwdAccumKey() {
        return dwdAccumKey;
    }

    public void setDwdAccumKey(int dwdAccumKey) {
        this.dwdAccumKey = dwdAccumKey;
    }

    public int getDwdLineKey() {
        return dwdLineKey;
    }

    public void setDwdLineKey(int dwdLineKey) {
        this.dwdLineKey = dwdLineKey;
    }

    public int getAccumNum() {
        return accumNum;
    }

    public void setAccumNum(int accumNum) {
        this.accumNum = accumNum;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public int getDecompClass5() {
        return decompClass5;
    }

    public void setDecompClass5(int decompClass5) {
        this.decompClass5 = decompClass5;
    }

    public String getSwdOriginCode() {
        return swdOriginCode;
    }

    public void setSwdOriginCode(String swdOriginCode) {
        this.swdOriginCode = swdOriginCode;
    }

    public boolean isBurned() {
        return burned;
    }

    public void setBurned(boolean burned) {
        this.burned = burned;
    }

    public int getPctConifer() {
        return pctConifer;
    }

    public void setPctConifer(int pctConifer) {
        this.pctConifer = pctConifer;
    }

    public int getPctHdwd() {
        return pctHdwd;
    }

    public void setPctHdwd(int pctHdwd) {
        this.pctHdwd = pctHdwd;
    }

    public DWDAccum(int dwdAccumKey, int dwdLineKey, int accumNum, double length, double depth, int decompClass5, String swdOriginCode, boolean burned, int pctConifer, int pctHdwd) {
        this.dwdAccumKey = dwdAccumKey;
        this.dwdLineKey = dwdLineKey;
        this.accumNum = accumNum;
        this.length = length;
        this.depth = depth;
        this.decompClass5 = decompClass5;
        this.swdOriginCode = swdOriginCode;
        this.burned = burned;
        this.pctConifer = pctConifer;
        this.pctHdwd = pctHdwd;
    }
}
