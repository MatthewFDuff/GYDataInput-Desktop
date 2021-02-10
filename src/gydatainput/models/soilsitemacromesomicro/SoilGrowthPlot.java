package gydatainput.models.soilsitemacromesomicro;

public class SoilGrowthPlot {
    private int soilGrowthPlotKey;
    private int plotKey;
    private int growthPlotNum;
    private int rockClassCode;
    private String surfStonClassCode;
    private String slopeTypeCode;
    private int slopePct;
    private int slopeAspect;
    private String groundSurfShapeCode;
    private int microClassCode;

    /**
     * SoilGrowthPlot
     * @param soilGrowthPlotKey
     * @param plotKey
     * @param growthPlotNum
     * @param rockClassCode
     * @param surfStonClassCode
     * @param slopeTypeCode
     * @param slopePct
     * @param slopeAspect
     * @param groundSurfShapeCode
     * @param microClassCode
     */
    public SoilGrowthPlot(int soilGrowthPlotKey, int plotKey, int growthPlotNum, int rockClassCode, String surfStonClassCode, String slopeTypeCode, int slopePct, int slopeAspect, String groundSurfShapeCode, int microClassCode) {
        this.soilGrowthPlotKey = soilGrowthPlotKey;
        this.plotKey = plotKey;
        this.growthPlotNum = growthPlotNum;
        this.rockClassCode = rockClassCode;
        this.surfStonClassCode = surfStonClassCode;
        this.slopeTypeCode = slopeTypeCode;
        this.slopePct = slopePct;
        this.slopeAspect = slopeAspect;
        this.groundSurfShapeCode = groundSurfShapeCode;
        this.microClassCode = microClassCode;
    }

    public int getSoilGrowthPlotKey() {
        return soilGrowthPlotKey;
    }

    public void setSoilGrowthPlotKey(int soilGrowthPlotKey) {
        this.soilGrowthPlotKey = soilGrowthPlotKey;
    }

    public int getPlotKey() {
        return plotKey;
    }

    public void setPlotKey(int plotKey) {
        this.plotKey = plotKey;
    }

    public int getGrowthPlotNum() {
        return growthPlotNum;
    }

    public void setGrowthPlotNum(int growthPlotNum) {
        this.growthPlotNum = growthPlotNum;
    }

    public int getRockClassCode() {
        return rockClassCode;
    }

    public void setRockClassCode(int rockClassCode) {
        this.rockClassCode = rockClassCode;
    }

    public String getSurfStonClassCode() {
        return surfStonClassCode;
    }

    public void setSurfStonClassCode(String surfStonClassCode) {
        this.surfStonClassCode = surfStonClassCode;
    }

    public String getSlopeTypeCode() {
        return slopeTypeCode;
    }

    public void setSlopeTypeCode(String slopeTypeCode) {
        this.slopeTypeCode = slopeTypeCode;
    }

    public int getSlopePct() {
        return slopePct;
    }

    public void setSlopePct(int slopePct) {
        this.slopePct = slopePct;
    }

    public int getSlopeAspect() {
        return slopeAspect;
    }

    public void setSlopeAspect(int slopeAspect) {
        this.slopeAspect = slopeAspect;
    }

    public String getGroundSurfShapeCode() {
        return groundSurfShapeCode;
    }

    public void setGroundSurfShapeCode(String groundSurfShapeCode) {
        this.groundSurfShapeCode = groundSurfShapeCode;
    }

    public int getMicroClassCode() {
        return microClassCode;
    }

    public void setMicroClassCode(int microClassCode) {
        this.microClassCode = microClassCode;
    }
}
