package gydatainput.models.vegetation;

public class VegCover {
    private int vegCoverKey;
    private int vegGroupCode;
    private int coverClass1To5Code;
    private int coverClass0To5Code;
    private int coverPct;

    public int getVegCoverKey() {
        return vegCoverKey;
    }

    public void setVegCoverKey(int vegCoverKey) {
        this.vegCoverKey = vegCoverKey;
    }

    public int getVegGroupCode() {
        return vegGroupCode;
    }

    public void setVegGroupCode(int vegGroupCode) {
        this.vegGroupCode = vegGroupCode;
    }

    public int getCoverClass1To5Code() {
        return coverClass1To5Code;
    }

    public void setCoverClass1To5Code(int coverClass1To5Code) {
        this.coverClass1To5Code = coverClass1To5Code;
    }

    public int getCoverClass0To5Code() {
        return coverClass0To5Code;
    }

    public void setCoverClass0To5Code(int coverClass0To5Code) {
        this.coverClass0To5Code = coverClass0To5Code;
    }

    public int getCoverPct() {
        return coverPct;
    }

    public void setCoverPct(int coverPct) {
        this.coverPct = coverPct;
    }

    public VegCover(int vegCoverKey, int vegGroupCode, int coverClass1To5Code, int coverClass0To5Code, int coverPct) {
        this.vegCoverKey = vegCoverKey;
        this.vegGroupCode = vegGroupCode;
        this.coverClass1To5Code = coverClass1To5Code;
        this.coverClass0To5Code = coverClass0To5Code;
        this.coverPct = coverPct;
    }
}
