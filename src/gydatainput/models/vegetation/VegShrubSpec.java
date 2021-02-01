package gydatainput.models.vegetation;

public class VegShrubSpec {
    private int vegShrubSpecKey;
    private int specCode;
    private String layerCode;
    private int coverPct;

    public int getVegShrubSpecKey() {
        return vegShrubSpecKey;
    }

    public void setVegShrubSpecKey(int vegShrubSpecKey) {
        this.vegShrubSpecKey = vegShrubSpecKey;
    }

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public String getLayerCode() {
        return layerCode;
    }

    public void setLayerCode(String layerCode) {
        this.layerCode = layerCode;
    }

    public int getCoverPct() {
        return coverPct;
    }

    public void setCoverPct(int coverPct) {
        this.coverPct = coverPct;
    }

    public VegShrubSpec(int vegShrubSpecKey, int specCode, String layerCode, int coverPct) {
        this.vegShrubSpecKey = vegShrubSpecKey;
        this.specCode = specCode;
        this.layerCode = layerCode;
        this.coverPct = coverPct;
    }
}
