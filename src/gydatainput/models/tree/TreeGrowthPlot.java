package gydatainput.models.tree;

public class TreeGrowthPlot {
    private int treeGrowthPlotKey;
    private int growthPlotNum;
    private int crownClsr;
    private int locAzi;
    private double locDist;
    private double radius;
    private double width;
    private double length;
    boolean treeRenumber;

    private TreeMsr[] treeMsrs;

    public TreeMsr[] getTreeMsrs() {
        return treeMsrs;
    }

    public void setTreeMsrs(TreeMsr[] treeMsrs) {
        this.treeMsrs = treeMsrs;
    }

    public int getTreeGrowthPlotKey() {
        return treeGrowthPlotKey;
    }

    public void setTreeGrowthPlotKey(int treeGrowthPlotKey) {
        this.treeGrowthPlotKey = treeGrowthPlotKey;
    }

    public int getGrowthPlotNum() {
        return growthPlotNum;
    }

    public void setGrowthPlotNum(int growthPlotNum) {
        this.growthPlotNum = growthPlotNum;
    }

    public int getCrownClsr() {
        return crownClsr;
    }

    public void setCrownClsr(int crownClsr) {
        this.crownClsr = crownClsr;
    }

    public int getLocAzi() {
        return locAzi;
    }

    public void setLocAzi(int locAzi) {
        this.locAzi = locAzi;
    }

    public double getLocDist() {
        return locDist;
    }

    public void setLocDist(double locDist) {
        this.locDist = locDist;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public boolean isTreeRenumber() {
        return treeRenumber;
    }

    public void setTreeRenumber(boolean treeRenumber) {
        this.treeRenumber = treeRenumber;
    }

    public TreeGrowthPlot(int treeGrowthPlotKey, int growthPlotNum, int crownClsr, int locAzi, double locDist, double radius, double width, double length, boolean treeRenumber) {
        this.treeGrowthPlotKey = treeGrowthPlotKey;
        this.growthPlotNum = growthPlotNum;
        this.crownClsr = crownClsr;
        this.locAzi = locAzi;
        this.locDist = locDist;
        this.radius = radius;
        this.width = width;
        this.length = length;
        this.treeRenumber = treeRenumber;
    }
}
