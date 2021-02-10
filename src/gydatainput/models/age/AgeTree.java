package gydatainput.models.age;

public class AgeTree {
    private int ageTreeKey;
    private int ageHeaderKey;
    private int growthPlotNum;
    private int treeNum;
    private String siteCondCode;
    private int specCode;
    private String treeOriginCode;
    private boolean vet;
    private double htToDBH;
    private double dbh;
    private String crownClassCode;
    private double htTot;
    private double htLiveFoliage;
    private double htLiveBranch;
    private int mortAzi;
    private double mortDist;

    private AgeSample[] ageSamples;

    public AgeTree(int ageTreeKey, int ageHeaderKey, int growthPlotNum, int treeNum, String siteCondCode, int specCode, String treeOriginCode, boolean vet, double htToDBH, double dbh, String crownClassCode, double htTot, double htLiveFoliage, double htLiveBranch, int mortAzi, double mortDist) {
        this.ageTreeKey = ageTreeKey;
        this.ageHeaderKey = ageHeaderKey;
        this.growthPlotNum = growthPlotNum;
        this.treeNum = treeNum;
        this.siteCondCode = siteCondCode;
        this.specCode = specCode;
        this.treeOriginCode = treeOriginCode;
        this.vet = vet;
        this.htToDBH = htToDBH;
        this.dbh = dbh;
        this.crownClassCode = crownClassCode;
        this.htTot = htTot;
        this.htLiveFoliage = htLiveFoliage;
        this.htLiveBranch = htLiveBranch;
        this.mortAzi = mortAzi;
        this.mortDist = mortDist;
    }

    public AgeSample[] getAgeSamples() {
        return ageSamples;
    }

    public void setAgeSamples(AgeSample[] ageSamples) {
        this.ageSamples = ageSamples;
    }

    public int getAgeTreeKey() {
        return ageTreeKey;
    }

    public void setAgeTreeKey(int ageTreeKey) {
        this.ageTreeKey = ageTreeKey;
    }

    public int getAgeHeaderKey() {
        return ageHeaderKey;
    }

    public void setAgeHeaderKey(int ageHeaderKey) {
        this.ageHeaderKey = ageHeaderKey;
    }

    public int getGrowthPlotNum() {
        return growthPlotNum;
    }

    public void setGrowthPlotNum(int growthPlotNum) {
        this.growthPlotNum = growthPlotNum;
    }

    public int getTreeNum() {
        return treeNum;
    }

    public void setTreeNum(int treeNum) {
        this.treeNum = treeNum;
    }

    public String getSiteCondCode() {
        return siteCondCode;
    }

    public void setSiteCondCode(String siteCondCode) {
        this.siteCondCode = siteCondCode;
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

    public boolean isVet() {
        return vet;
    }

    public void setVet(boolean vet) {
        this.vet = vet;
    }

    public double getHtToDBH() {
        return htToDBH;
    }

    public void setHtToDBH(double htToDBH) {
        this.htToDBH = htToDBH;
    }

    public double getDbh() {
        return dbh;
    }

    public void setDbh(double dbh) {
        this.dbh = dbh;
    }

    public String getCrownClassCode() {
        return crownClassCode;
    }

    public void setCrownClassCode(String crownClassCode) {
        this.crownClassCode = crownClassCode;
    }

    public double getHtTot() {
        return htTot;
    }

    public void setHtTot(double htTot) {
        this.htTot = htTot;
    }

    public double getHtLiveFoliage() {
        return htLiveFoliage;
    }

    public void setHtLiveFoliage(double htLiveFoliage) {
        this.htLiveFoliage = htLiveFoliage;
    }

    public double getHtLiveBranch() {
        return htLiveBranch;
    }

    public void setHtLiveBranch(double htLiveBranch) {
        this.htLiveBranch = htLiveBranch;
    }

    public int getMortAzi() {
        return mortAzi;
    }

    public void setMortAzi(int mortAzi) {
        this.mortAzi = mortAzi;
    }

    public double getMortDist() {
        return mortDist;
    }

    public void setMortDist(double mortDist) {
        this.mortDist = mortDist;
    }
}
