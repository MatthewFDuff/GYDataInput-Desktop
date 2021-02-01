package gydatainput.models.vegetation;

public class VegVType {
    private int vegVTypeKey;
    private int growthPlotNum;
    private int vegTypeCode;
    private int elcMethodCode;

    public int getVegVTypeKey() {
        return vegVTypeKey;
    }

    public void setVegVTypeKey(int vegVTypeKey) {
        this.vegVTypeKey = vegVTypeKey;
    }

    public int getGrowthPlotNum() {
        return growthPlotNum;
    }

    public void setGrowthPlotNum(int growthPlotNum) {
        this.growthPlotNum = growthPlotNum;
    }

    public int getVegTypeCode() {
        return vegTypeCode;
    }

    public void setVegTypeCode(int vegTypeCode) {
        this.vegTypeCode = vegTypeCode;
    }

    public int getElcMethodCode() {
        return elcMethodCode;
    }

    public void setElcMethodCode(int elcMethodCode) {
        this.elcMethodCode = elcMethodCode;
    }

    public VegVType(int vegVTypeKey, int growthPlotNum, int vegTypeCode, int elcMethodCode) {
        this.vegVTypeKey = vegVTypeKey;
        this.growthPlotNum = growthPlotNum;
        this.vegTypeCode = vegTypeCode;
        this.elcMethodCode = elcMethodCode;
    }
}
