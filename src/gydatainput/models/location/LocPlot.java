package gydatainput.models.location;

public class LocPlot {
    private int locPlotKey;
    private int specCode;
    private String plotShapeCode;
    private int zone;
    private int latestLocatorMapYear;
    private int latestWalkInMapYear;

    public int getLocPlotKey() {
        return locPlotKey;
    }

    public void setLocPlotKey(int locPlotKey) {
        this.locPlotKey = locPlotKey;
    }

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public String getPlotShapeCode() {
        return plotShapeCode;
    }

    public void setPlotShapeCode(String plotShapeCode) {
        this.plotShapeCode = plotShapeCode;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public int getLatestLocatorMapYear() {
        return latestLocatorMapYear;
    }

    public void setLatestLocatorMapYear(int latestLocatorMapYear) {
        this.latestLocatorMapYear = latestLocatorMapYear;
    }

    public int getLatestWalkInMapYear() {
        return latestWalkInMapYear;
    }

    public void setLatestWalkInMapYear(int latestWalkInMapYear) {
        this.latestWalkInMapYear = latestWalkInMapYear;
    }

    public LocPlot(int locPlotKey, int specCode, String plotShapeCode, int zone, int latestLocatorMapYear, int latestWalkInMapYear) {
        this.locPlotKey = locPlotKey;
        this.specCode = specCode;
        this.plotShapeCode = plotShapeCode;
        this.zone = zone;
        this.latestLocatorMapYear = latestLocatorMapYear;
        this.latestWalkInMapYear = latestWalkInMapYear;
    }
}
