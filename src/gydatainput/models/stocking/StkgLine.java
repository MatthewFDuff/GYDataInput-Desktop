package gydatainput.models.stocking;

public class StkgLine {
    private int stkgLineKey;
    private int stkgHeaderKey;
    private int lineNum;
    private int azi;
    private int stkgPlotCount;

    private Stkg[] stkgs;

    // StkgLine Constructor
    public StkgLine(int stkgLineKey, int stkgHeaderKey, int lineNum, int azi, int stkgPlotCount) {
        this.stkgLineKey = stkgLineKey;
        this.stkgHeaderKey = stkgHeaderKey;
        this.lineNum = lineNum;
        this.azi = azi;
        this.stkgPlotCount = stkgPlotCount;
    }

    public int getStkgLineKey() {
        return stkgLineKey;
    }

    public void setStkgLineKey(int stkgLineKey) {
        this.stkgLineKey = stkgLineKey;
    }

    public int getStkgHeaderKey() {
        return stkgHeaderKey;
    }

    public void setStkgHeaderKey(int stkgHeaderKey) {
        this.stkgHeaderKey = stkgHeaderKey;
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

    public int getStkgPlotCount() {
        return stkgPlotCount;
    }

    public void setStkgPlotCount(int stkgPlotCount) {
        this.stkgPlotCount = stkgPlotCount;
    }

    public Stkg[] getStkgs() {
        return stkgs;
    }

    public void setStkgs(Stkg[] stkgs) {
        this.stkgs = stkgs;
    }

}
