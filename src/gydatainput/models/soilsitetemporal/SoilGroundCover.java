package gydatainput.models.soilsitetemporal;

public class SoilGroundCover {
    private int soilGroundCoverKey;
    private int soilHeaderKey;
    private int growthPlotKey;
    private int groundCoverTypeCode;
    private int coverClass1To5Code;
    private int pct;

    /**
     * SoilGroundCover Constructor
     * @param soilGroundCoverKey
     * @param soilHeaderKey
     * @param growthPlotKey
     * @param groundCoverTypeCode
     * @param coverClass1To5Code
     * @param pct
     */
    public SoilGroundCover(int soilGroundCoverKey, int soilHeaderKey, int growthPlotKey, int groundCoverTypeCode, int coverClass1To5Code, int pct) {
        this.soilGroundCoverKey = soilGroundCoverKey;
        this.soilHeaderKey = soilHeaderKey;
        this.growthPlotKey = growthPlotKey;
        this.groundCoverTypeCode = groundCoverTypeCode;
        this.coverClass1To5Code = coverClass1To5Code;
        this.pct = pct;
    }

    public int getSoilGroundCoverKey() {
        return soilGroundCoverKey;
    }

    public void setSoilGroundCoverKey(int soilGroundCoverKey) {
        this.soilGroundCoverKey = soilGroundCoverKey;
    }

    public int getSoilHeaderKey() {
        return soilHeaderKey;
    }

    public void setSoilHeaderKey(int soilHeaderKey) {
        this.soilHeaderKey = soilHeaderKey;
    }

    public int getGrowthPlotKey() {
        return growthPlotKey;
    }

    public void setGrowthPlotKey(int growthPlotKey) {
        this.growthPlotKey = growthPlotKey;
    }

    public int getGroundCoverTypeCode() {
        return groundCoverTypeCode;
    }

    public void setGroundCoverTypeCode(int groundCoverTypeCode) {
        this.groundCoverTypeCode = groundCoverTypeCode;
    }

    public int getCoverClass1To5Code() {
        return coverClass1To5Code;
    }

    public void setCoverClass1To5Code(int coverClass1To5Code) {
        this.coverClass1To5Code = coverClass1To5Code;
    }

    public int getPct() {
        return pct;
    }

    public void setPct(int pct) {
        this.pct = pct;
    }
}
