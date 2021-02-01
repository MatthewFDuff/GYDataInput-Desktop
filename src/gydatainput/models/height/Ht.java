package gydatainput.models.height;

public class Ht {
    private int htKey;
    private int htHeaderKey;
    private int treeMsrKey;
    private double offsetDist;
    private double htTot;
    private double htLiveFoliage;
    private double htLiveBranch;
    private double htMerch;

    public int getHtKey() {
        return htKey;
    }

    public void setHtKey(int htKey) {
        this.htKey = htKey;
    }

    public int getHtHeaderKey() {
        return htHeaderKey;
    }

    public void setHtHeaderKey(int htHeaderKey) {
        this.htHeaderKey = htHeaderKey;
    }

    public int getTreeMsrKey() {
        return treeMsrKey;
    }

    public void setTreeMsrKey(int treeMsrKey) {
        this.treeMsrKey = treeMsrKey;
    }

    public double getOffsetDist() {
        return offsetDist;
    }

    public void setOffsetDist(double offsetDist) {
        this.offsetDist = offsetDist;
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

    public double getHtMerch() {
        return htMerch;
    }

    public void setHtMerch(double htMerch) {
        this.htMerch = htMerch;
    }

    // CONSTRUCTOR
    public Ht(int htKey, int htHeaderKey, int treeMsrKey, double offsetDist, double htTot, double htLiveFoliage, double htLiveBranch, double htMerch) {
        this.htKey = htKey;
        this.htHeaderKey = htHeaderKey;
        this.treeMsrKey = treeMsrKey;
        this.offsetDist = offsetDist;
        this.htTot = htTot;
        this.htLiveFoliage = htLiveFoliage;
        this.htLiveBranch = htLiveBranch;
        this.htMerch = htMerch;
    }
}
