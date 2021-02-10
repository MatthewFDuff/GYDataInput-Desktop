package gydatainput.models.soilsitemacromesomicro;

public class SoilPlot {
    private int soilPlotKey;
    private int plotKey;
    private int landformCode;
    private String slopeTopoClassCode;
    private int slopePosnCode;
    private String slopeShapeCode;
    private String slopeTypeCode;
    private int slopePct;
    private int slopeAspect;

    /**
     * SoilPlot Constructor
     * @param soilPlotKey
     * @param plotKey
     * @param landformCode
     * @param slopeTopoClassCode
     * @param slopePosnCode
     * @param slopeShapeCode
     * @param slopeTypeCode
     * @param slopePct
     * @param slopeAspect
     */
    public SoilPlot(int soilPlotKey, int plotKey, int landformCode, String slopeTopoClassCode, int slopePosnCode, String slopeShapeCode, String slopeTypeCode, int slopePct, int slopeAspect) {
        this.soilPlotKey = soilPlotKey;
        this.plotKey = plotKey;
        this.landformCode = landformCode;
        this.slopeTopoClassCode = slopeTopoClassCode;
        this.slopePosnCode = slopePosnCode;
        this.slopeShapeCode = slopeShapeCode;
        this.slopeTypeCode = slopeTypeCode;
        this.slopePct = slopePct;
        this.slopeAspect = slopeAspect;
    }

    public int getSoilPlotKey() {
        return soilPlotKey;
    }

    public void setSoilPlotKey(int soilPlotKey) {
        this.soilPlotKey = soilPlotKey;
    }

    public int getPlotKey() {
        return plotKey;
    }

    public void setPlotKey(int plotKey) {
        this.plotKey = plotKey;
    }

    public int getLandformCode() {
        return landformCode;
    }

    public void setLandformCode(int landformCode) {
        this.landformCode = landformCode;
    }

    public String getSlopeTopoClassCode() {
        return slopeTopoClassCode;
    }

    public void setSlopeTopoClassCode(String slopeTopoClassCode) {
        this.slopeTopoClassCode = slopeTopoClassCode;
    }

    public int getSlopePosnCode() {
        return slopePosnCode;
    }

    public void setSlopePosnCode(int slopePosnCode) {
        this.slopePosnCode = slopePosnCode;
    }

    public String getSlopeShapeCode() {
        return slopeShapeCode;
    }

    public void setSlopeShapeCode(String slopeShapeCode) {
        this.slopeShapeCode = slopeShapeCode;
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
}
