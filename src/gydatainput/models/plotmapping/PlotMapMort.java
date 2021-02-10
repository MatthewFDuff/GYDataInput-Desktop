package gydatainput.models.plotmapping;

public class PlotMapMort {
    private int plotMapMortKey;
    private int plotKey;
    private int numOfSections;
    private boolean equalSections;

    /**
     * PlotMapMort Constructor
     * @param plotMapMortKey
     * @param plotKey
     * @param numOfSections
     * @param equalSections
     */
    public PlotMapMort(int plotMapMortKey, int plotKey, int numOfSections, boolean equalSections) {
        this.plotMapMortKey = plotMapMortKey;
        this.plotKey = plotKey;
        this.numOfSections = numOfSections;
        this.equalSections = equalSections;
    }

    public int getPlotMapMortKey() {
        return plotMapMortKey;
    }

    public void setPlotMapMortKey(int plotMapMortKey) {
        this.plotMapMortKey = plotMapMortKey;
    }

    public int getPlotKey() {
        return plotKey;
    }

    public void setPlotKey(int plotKey) {
        this.plotKey = plotKey;
    }

    public int getNumOfSections() {
        return numOfSections;
    }

    public void setNumOfSections(int numOfSections) {
        this.numOfSections = numOfSections;
    }

    public boolean isEqualSections() {
        return equalSections;
    }

    public void setEqualSections(boolean equalSections) {
        this.equalSections = equalSections;
    }
}
