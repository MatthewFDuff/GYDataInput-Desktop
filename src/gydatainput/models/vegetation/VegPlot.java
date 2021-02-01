package gydatainput.models.vegetation;

public class VegPlot {
    private int vegPlotKey;
    private int growthPlotNum;
    private int vegPlotNum;
    private int shrubPlotNum;
    private int regenPlotNum;
    private String quadCode;
    private int lineNum;
    private int azi;
    private double dist;

    private VegCover[] vegCovers;
    private VegRegen[] vegRegens;
    private VegSpecPres[] vegSpecPres;
    private VegShrubSpec[] vegShrubSpecs;

    public VegCover[] getVegCovers() {
        return vegCovers;
    }

    public void setVegCovers(VegCover[] vegCovers) {
        this.vegCovers = vegCovers;
    }

    public VegRegen[] getVegRegens() {
        return vegRegens;
    }

    public void setVegRegens(VegRegen[] vegRegens) {
        this.vegRegens = vegRegens;
    }

    public VegSpecPres[] getVegSpecPres() {
        return vegSpecPres;
    }

    public void setVegSpecPres(VegSpecPres[] vegSpecPres) {
        this.vegSpecPres = vegSpecPres;
    }

    public VegShrubSpec[] getVegShrubSpecs() {
        return vegShrubSpecs;
    }

    public void setVegShrubSpecs(VegShrubSpec[] vegShrubSpecs) {
        this.vegShrubSpecs = vegShrubSpecs;
    }

    public int getVegPlotKey() {
        return vegPlotKey;
    }

    public void setVegPlotKey(int vegPlotKey) {
        this.vegPlotKey = vegPlotKey;
    }

    public int getGrowthPlotNum() {
        return growthPlotNum;
    }

    public void setGrowthPlotNum(int growthPlotNum) {
        this.growthPlotNum = growthPlotNum;
    }

    public int getVegPlotNum() {
        return vegPlotNum;
    }

    public void setVegPlotNum(int vegPlotNum) {
        this.vegPlotNum = vegPlotNum;
    }

    public int getShrubPlotNum() {
        return shrubPlotNum;
    }

    public void setShrubPlotNum(int shrubPlotNum) {
        this.shrubPlotNum = shrubPlotNum;
    }

    public int getRegenPlotNum() {
        return regenPlotNum;
    }

    public void setRegenPlotNum(int regenPlotNum) {
        this.regenPlotNum = regenPlotNum;
    }

    public String getQuadCode() {
        return quadCode;
    }

    public void setQuadCode(String quadCode) {
        this.quadCode = quadCode;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getAzi() {
        return azi;
    }

    public void setAzi(int azi) {
        this.azi = azi;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public VegPlot(int vegPlotKey, int growthPlotNum, int vegPlotNum, int shrubPlotNum, int regenPlotNum, String quadCode, int lineNum, int azi, double dist) {
        this.vegPlotKey = vegPlotKey;
        this.growthPlotNum = growthPlotNum;
        this.vegPlotNum = vegPlotNum;
        this.shrubPlotNum = shrubPlotNum;
        this.regenPlotNum = regenPlotNum;
        this.quadCode = quadCode;
        this.lineNum = lineNum;
        this.azi = azi;
        this.dist = dist;
    }
}
