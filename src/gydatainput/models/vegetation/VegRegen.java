package gydatainput.models.vegetation;

public class VegRegen {
    private int vegRegenKey;
    private int regenTypeCode;
    private int specCode;
    private int regenCount;
    private int coverPct;

    public int getVegRegenKey() {
        return vegRegenKey;
    }

    public void setVegRegenKey(int vegRegenKey) {
        this.vegRegenKey = vegRegenKey;
    }

    public int getRegenTypeCode() {
        return regenTypeCode;
    }

    public void setRegenTypeCode(int regenTypeCode) {
        this.regenTypeCode = regenTypeCode;
    }

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public int getRegenCount() {
        return regenCount;
    }

    public void setRegenCount(int regenCount) {
        this.regenCount = regenCount;
    }

    public int getCoverPct() {
        return coverPct;
    }

    public void setCoverPct(int coverPct) {
        this.coverPct = coverPct;
    }

    public VegRegen(int vegRegenKey, int regenTypeCode, int specCode, int regenCount, int coverPct) {
        this.vegRegenKey = vegRegenKey;
        this.regenTypeCode = regenTypeCode;
        this.specCode = specCode;
        this.regenCount = regenCount;
        this.coverPct = coverPct;
    }
}
