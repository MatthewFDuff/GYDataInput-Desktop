package gydatainput.models.mortality;

public class MortTree {
    private int mortTreeKey;
    private int plotMapMortKey;
    private int section;
    private int mortTreeNum;
    private int specCode;
    private double dbh;
    private int mortCauseCode;



    public MortTree(int mortTreeKey, int plotMapMortKey, int section, int mortTreeNum, int specCode, double dbh, int mortCauseCode) {
        this.mortTreeKey = mortTreeKey;
        this.plotMapMortKey = plotMapMortKey;
        this.section = section;
        this.mortTreeNum = mortTreeNum;
        this.specCode = specCode;
        this.dbh = dbh;
        this.mortCauseCode = mortCauseCode;
    }

    public int getMortTreeKey() {
        return mortTreeKey;
    }

    public void setMortTreeKey(int mortTreeKey) {
        this.mortTreeKey = mortTreeKey;
    }

    public int getPlotMapMortKey() {
        return plotMapMortKey;
    }

    public void setPlotMapMortKey(int plotMapMortKey) {
        this.plotMapMortKey = plotMapMortKey;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getMortTreeNum() {
        return mortTreeNum;
    }

    public void setMortTreeNum(int mortTreeNum) {
        this.mortTreeNum = mortTreeNum;
    }

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public double getDbh() {
        return dbh;
    }

    public void setDbh(double dbh) {
        this.dbh = dbh;
    }

    public int getMortCauseCode() {
        return mortCauseCode;
    }

    public void setMortCauseCode(int mortCauseCode) {
        this.mortCauseCode = mortCauseCode;
    }
}
