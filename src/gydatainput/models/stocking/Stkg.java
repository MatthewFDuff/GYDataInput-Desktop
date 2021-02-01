package gydatainput.models.stocking;

public class Stkg {
    private int stkgKey;
    private int stkgLineKey;
    private int stkgPlotNum;
    private int specCode;
    private int stkgSizeCode;

    // Stkg Constructor
    public Stkg(int stkgKey, int stkgLineKey, int stkgPlotNum, int specCode, int stkgSizeCode) {
        this.stkgKey = stkgKey;
        this.stkgLineKey = stkgLineKey;
        this.stkgPlotNum = stkgPlotNum;
        this.specCode = specCode;
        this.stkgSizeCode = stkgSizeCode;
    }

    public int getStkgKey() {
        return stkgKey;
    }

    public void setStkgKey(int stkgKey) {
        this.stkgKey = stkgKey;
    }

    public int getStkgLineKey() {
        return stkgLineKey;
    }

    public void setStkgLineKey(int stkgLineKey) {
        this.stkgLineKey = stkgLineKey;
    }

    public int getStkgPlotNum() {
        return stkgPlotNum;
    }

    public void setStkgPlotNum(int stkgPlotNum) {
        this.stkgPlotNum = stkgPlotNum;
    }

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public int getStkgSizeCode() {
        return stkgSizeCode;
    }

    public void setStkgSizeCode(int stkgSizeCode) {
        this.stkgSizeCode = stkgSizeCode;
    }

}
