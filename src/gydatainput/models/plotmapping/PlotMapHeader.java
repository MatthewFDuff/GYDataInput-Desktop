package gydatainput.models.plotmapping;

public class PlotMapHeader {
    private int plotMapHeaderKey;
    private int visitKey;
    private String msrDate;
    private int areaAssessed;

    /**
     * PlotMapHeader Constructor
     * @param plotMapHeaderKey
     * @param visitKey
     * @param msrDate
     * @param areaAssessed
     */
    public PlotMapHeader(int plotMapHeaderKey, int visitKey, String msrDate, int areaAssessed) {
        this.plotMapHeaderKey = plotMapHeaderKey;
        this.visitKey = visitKey;
        this.msrDate = msrDate;
        this.areaAssessed = areaAssessed;
    }

    public int getPlotMapHeaderKey() {
        return plotMapHeaderKey;
    }

    public void setPlotMapHeaderKey(int plotMapHeaderKey) {
        this.plotMapHeaderKey = plotMapHeaderKey;
    }

    public int getVisitKey() {
        return visitKey;
    }

    public void setVisitKey(int visitKey) {
        this.visitKey = visitKey;
    }

    public String getMsrDate() {
        return msrDate;
    }

    public void setMsrDate(String msrDate) {
        this.msrDate = msrDate;
    }

    public int getAreaAssessed() {
        return areaAssessed;
    }

    public void setAreaAssessed(int areaAssessed) {
        this.areaAssessed = areaAssessed;
    }
}
