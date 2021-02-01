package gydatainput.models.standinformation;

public class StandInfoCompr {
    private int standInfoComprKey;
    private int comprTypeCode;
    private int comprYear;

    public int getStandInfoComprKey() {
        return standInfoComprKey;
    }

    public void setStandInfoComprKey(int standInfoComprKey) {
        this.standInfoComprKey = standInfoComprKey;
    }

    public int getComprTypeCode() {
        return comprTypeCode;
    }

    public void setComprTypeCode(int comprTypeCode) {
        this.comprTypeCode = comprTypeCode;
    }

    public int getComprYear() {
        return comprYear;
    }

    public void setComprYear(int comprYear) {
        this.comprYear = comprYear;
    }

    public StandInfoCompr(int standInfoComprKey, int comprTypeCode, int comprYear) {
        this.standInfoComprKey = standInfoComprKey;
        this.comprTypeCode = comprTypeCode;
        this.comprYear = comprYear;
    }
}
