package gydatainput.models.downwoodydebris;

public class DWDStump {
    private int dwdStumpKey;
    private int dwdLineKey;
    private int specCode;
    private int stumpCount;

    public int getDwdStumpKey() {
        return dwdStumpKey;
    }

    public void setDwdStumpKey(int dwdStumpKey) {
        this.dwdStumpKey = dwdStumpKey;
    }

    public int getDwdLineKey() {
        return dwdLineKey;
    }

    public void setDwdLineKey(int dwdLineKey) {
        this.dwdLineKey = dwdLineKey;
    }

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public int getStumpCount() {
        return stumpCount;
    }

    public void setStumpCount(int stumpCount) {
        this.stumpCount = stumpCount;
    }

    public DWDStump(int dwdStumpKey, int dwdLineKey, int specCode, int stumpCount) {
        this.dwdStumpKey = dwdStumpKey;
        this.dwdLineKey = dwdLineKey;
        this.specCode = specCode;
        this.stumpCount = stumpCount;
    }
}
