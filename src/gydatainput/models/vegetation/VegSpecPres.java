package gydatainput.models.vegetation;

public class VegSpecPres {
    private int vegSpecPresKey;
    private int specCode;

    public int getVegSpecPresKey() {
        return vegSpecPresKey;
    }

    public void setVegSpecPresKey(int vegSpecPresKey) {
        this.vegSpecPresKey = vegSpecPresKey;
    }

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public VegSpecPres(int vegSpecPresKey, int specCode) {
        this.vegSpecPresKey = vegSpecPresKey;
        this.specCode = specCode;
    }
}
