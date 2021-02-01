package gydatainput.models.tree;

import gydatainput.models.plotmapping.PlotMapGrowthPlot;

/**
 * The Tree class contains data for each individual tree.
 *
 * @author Matthew Duff
 * @version 0.1
 * @since 2020-12-01
 * */
public class Tree {
    private int plotMapGrowthPlotKey;
    private int section;
    private int treeNum;
    private String tagTypeCode;
    private int specCode;
    private String treeOriginCode;
    private int postNum;
    private double dist;
    private int azi;
    private int mortCauseCode;
    private String origTreeNum;

    private PlotMapGrowthPlot plotMapGrowthPlot;

    public int getPlotMapGrowthPlotKey() {
        return plotMapGrowthPlotKey;
    }

    public void setPlotMapGrowthPlotKey(int plotMapGrowthPlotKey) {
        this.plotMapGrowthPlotKey = plotMapGrowthPlotKey;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getTreeNum() {
        return treeNum;
    }

    public void setTreeNum(int treeNum) {
        this.treeNum = treeNum;
    }

    public String getTagTypeCode() {
        return tagTypeCode;
    }

    public void setTagTypeCode(String tagTypeCode) {
        this.tagTypeCode = tagTypeCode;
    }

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public String getTreeOriginCode() {
        return treeOriginCode;
    }

    public void setTreeOriginCode(String treeOriginCode) {
        this.treeOriginCode = treeOriginCode;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public int getAzi() {
        return azi;
    }

    public void setAzi(int azi) {
        this.azi = azi;
    }

    public int getMortCauseCode() {
        return mortCauseCode;
    }

    public void setMortCauseCode(int mortCauseCode) {
        this.mortCauseCode = mortCauseCode;
    }

    public String getOrigTreeNum() {
        return origTreeNum;
    }

    public void setOrigTreeNum(String origTreeNum) {
        this.origTreeNum = origTreeNum;
    }

    public Tree(int plotMapGrowthPlotKey, int section, int treeNum, String tagTypeCode, int specCode, String treeOriginCode, int postNum, double dist, int azi, int mortCauseCode, String origTreeNum) {
        this.plotMapGrowthPlotKey = plotMapGrowthPlotKey;
        this.section = section;
        this.treeNum = treeNum;
        this.tagTypeCode = tagTypeCode;
        this.specCode = specCode;
        this.treeOriginCode = treeOriginCode;
        this.postNum = postNum;
        this.dist = dist;
        this.azi = azi;
        this.mortCauseCode = mortCauseCode;
        this.origTreeNum = origTreeNum;
    }
}
