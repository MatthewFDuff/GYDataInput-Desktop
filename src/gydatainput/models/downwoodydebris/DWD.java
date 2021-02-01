package gydatainput.models.downwoodydebris;

public class DWD {
    private int dwdKey;
    private int dwdHeaderKey;
    private int cwdNum;
    private int specCode;
    private int decompClass5;
    private String cwdOriginCode;
    private double cwdLength;
    private double smDia;
    private double lgDia;
    private boolean moss;
    private boolean burned;
    private boolean hollow;
    private int decompClass3;
    private double distFirst;
    private double distLast;
    private boolean wildfire;

    private DWDIntersect[] dwdIntersections;

    public DWDIntersect[] getDwdIntersections() {
        return dwdIntersections;
    }

    public void setDwdIntersections(DWDIntersect[] dwdIntersections) {
        this.dwdIntersections = dwdIntersections;
    }

    public int getDwdKey() {
        return dwdKey;
    }

    public void setDwdKey(int dwdKey) {
        this.dwdKey = dwdKey;
    }

    public int getDwdHeaderKey() {
        return dwdHeaderKey;
    }

    public void setDwdHeaderKey(int dwdHeaderKey) {
        this.dwdHeaderKey = dwdHeaderKey;
    }

    public int getCwdNum() {
        return cwdNum;
    }

    public void setCwdNum(int cwdNum) {
        this.cwdNum = cwdNum;
    }

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public int getDecompClass5() {
        return decompClass5;
    }

    public void setDecompClass5(int decompClass5) {
        this.decompClass5 = decompClass5;
    }

    public String getCwdOriginCode() {
        return cwdOriginCode;
    }

    public void setCwdOriginCode(String cwdOriginCode) {
        this.cwdOriginCode = cwdOriginCode;
    }

    public double getCwdLength() {
        return cwdLength;
    }

    public void setCwdLength(double cwdLength) {
        this.cwdLength = cwdLength;
    }

    public double getSmDia() {
        return smDia;
    }

    public void setSmDia(double smDia) {
        this.smDia = smDia;
    }

    public double getLgDia() {
        return lgDia;
    }

    public void setLgDia(double lgDia) {
        this.lgDia = lgDia;
    }

    public boolean isMoss() {
        return moss;
    }

    public void setMoss(boolean moss) {
        this.moss = moss;
    }

    public boolean isBurned() {
        return burned;
    }

    public void setBurned(boolean burned) {
        this.burned = burned;
    }

    public boolean isHollow() {
        return hollow;
    }

    public void setHollow(boolean hollow) {
        this.hollow = hollow;
    }

    public int getDecompClass3() {
        return decompClass3;
    }

    public void setDecompClass3(int decompClass3) {
        this.decompClass3 = decompClass3;
    }

    public double getDistFirst() {
        return distFirst;
    }

    public void setDistFirst(double distFirst) {
        this.distFirst = distFirst;
    }

    public double getDistLast() {
        return distLast;
    }

    public void setDistLast(double distLast) {
        this.distLast = distLast;
    }

    public boolean isWildfire() {
        return wildfire;
    }

    public void setWildfire(boolean wildfire) {
        this.wildfire = wildfire;
    }

    public DWD(int dwdKey, int dwdHeaderKey, int cwdNum, int specCode, int decompClass5, String cwdOriginCode, double cwdLength, double smDia, double lgDia, boolean moss, boolean burned, boolean hollow, int decompClass3, double distFirst, double distLast, boolean wildfire) {
        this.dwdKey = dwdKey;
        this.dwdHeaderKey = dwdHeaderKey;
        this.cwdNum = cwdNum;
        this.specCode = specCode;
        this.decompClass5 = decompClass5;
        this.cwdOriginCode = cwdOriginCode;
        this.cwdLength = cwdLength;
        this.smDia = smDia;
        this.lgDia = lgDia;
        this.moss = moss;
        this.burned = burned;
        this.hollow = hollow;
        this.decompClass3 = decompClass3;
        this.distFirst = distFirst;
        this.distLast = distLast;
        this.wildfire = wildfire;
    }
}
