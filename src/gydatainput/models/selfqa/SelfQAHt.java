package gydatainput.models.selfqa;

public class SelfQAHt {
    private int selfQAHtKey;
    private int selfQAHeaderKey;
    private int htKey;
    private String qaRowCode;
    private double offsetDist;
    private double htTot;
    private double htLiveFoliage;
    private double htLiveBranch;

    /**
     * SelfQAHt Constructor
     * @param selfQAHtKey
     * @param selfQAHeaderKey
     * @param htKey
     * @param qaRowCode
     * @param offsetDist
     * @param htTot
     * @param htLiveFoliage
     * @param htLiveBranch
     */
    public SelfQAHt(int selfQAHtKey, int selfQAHeaderKey, int htKey, String qaRowCode, double offsetDist, double htTot, double htLiveFoliage, double htLiveBranch) {
        this.selfQAHtKey = selfQAHtKey;
        this.selfQAHeaderKey = selfQAHeaderKey;
        this.htKey = htKey;
        this.qaRowCode = qaRowCode;
        this.offsetDist = offsetDist;
        this.htTot = htTot;
        this.htLiveFoliage = htLiveFoliage;
        this.htLiveBranch = htLiveBranch;
    }

    public int getSelfQAHtKey() {
        return selfQAHtKey;
    }

    public void setSelfQAHtKey(int selfQAHtKey) {
        this.selfQAHtKey = selfQAHtKey;
    }

    public int getSelfQAHeaderKey() {
        return selfQAHeaderKey;
    }

    public void setSelfQAHeaderKey(int selfQAHeaderKey) {
        this.selfQAHeaderKey = selfQAHeaderKey;
    }

    public int getHtKey() {
        return htKey;
    }

    public void setHtKey(int htKey) {
        this.htKey = htKey;
    }

    public String getQaRowCode() {
        return qaRowCode;
    }

    public void setQaRowCode(String qaRowCode) {
        this.qaRowCode = qaRowCode;
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
}
