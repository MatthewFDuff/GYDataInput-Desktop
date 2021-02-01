package gydatainput.models.tree;

public class TreeHeader {
    private int treeHeaderKey;
    private String msrDate;

    private TreeGrowthPlot[] treeGrowthPlot;

    public TreeGrowthPlot[] getTreeGrowthPlot() {
        return treeGrowthPlot;
    }

    public void setTreeGrowthPlot(TreeGrowthPlot[] treeGrowthPlot) {
        this.treeGrowthPlot = treeGrowthPlot;
    }

    public int getTreeHeaderKey() {
        return treeHeaderKey;
    }

    public void setTreeHeaderKey(int treeHeaderKey) {
        this.treeHeaderKey = treeHeaderKey;
    }

    public String getMsrDate() {
        return msrDate;
    }

    public void setMsrDate(String msrDate) {
        this.msrDate = msrDate;
    }

    public TreeHeader(int treeHeaderKey, String msrDate) {
        this.treeHeaderKey = treeHeaderKey;
        this.msrDate = msrDate;
    }
}
