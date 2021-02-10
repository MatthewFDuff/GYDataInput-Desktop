package gydatainput.models.plotmapping;

public class PlotMapGrowthPlot {
    private int plotMapGrowthPlotKey;
    private int plotKey;
    private int growthPlotNum;
    private int numOfSections;
    private boolean equalSections;

    /**
     * PlotMapGrowthPlot Constructor
     * @param plotMapGrowthPlotKey
     * @param plotKey
     * @param growthPlotNum
     * @param numOfSections
     * @param equalSections
     */
    public PlotMapGrowthPlot(int plotMapGrowthPlotKey, int plotKey, int growthPlotNum, int numOfSections, boolean equalSections) {
        this.plotMapGrowthPlotKey = plotMapGrowthPlotKey;
        this.plotKey = plotKey;
        this.growthPlotNum = growthPlotNum;
        this.numOfSections = numOfSections;
        this.equalSections = equalSections;
    }

    public int getPlotMapGrowthPlotKey() {
        return plotMapGrowthPlotKey;
    }

    public void setPlotMapGrowthPlotKey(int plotMapGrowthPlotKey) {
        this.plotMapGrowthPlotKey = plotMapGrowthPlotKey;
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
