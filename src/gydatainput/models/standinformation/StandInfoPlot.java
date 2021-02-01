package gydatainput.models.standinformation;

public class StandInfoPlot {
    private int standInfoPlotKey;
    private boolean geneticImproved;
    private int seedGeneration;
    private int geneticWorth;

    public int getStandInfoPlotKey() {
        return standInfoPlotKey;
    }

    public void setStandInfoPlotKey(int standInfoPlotKey) {
        this.standInfoPlotKey = standInfoPlotKey;
    }

    public boolean isGeneticImproved() {
        return geneticImproved;
    }

    public void setGeneticImproved(boolean geneticImproved) {
        this.geneticImproved = geneticImproved;
    }

    public int getSeedGeneration() {
        return seedGeneration;
    }

    public void setSeedGeneration(int seedGeneration) {
        this.seedGeneration = seedGeneration;
    }

    public int getGeneticWorth() {
        return geneticWorth;
    }

    public void setGeneticWorth(int geneticWorth) {
        this.geneticWorth = geneticWorth;
    }

    public StandInfoPlot(int standInfoPlotKey, boolean geneticImproved, int seedGeneration, int geneticWorth) {
        this.standInfoPlotKey = standInfoPlotKey;
        this.geneticImproved = geneticImproved;
        this.seedGeneration = seedGeneration;
        this.geneticWorth = geneticWorth;
    }
}
