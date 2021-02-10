package gydatainput.models.selfqa;

public class SelfQADeform {
    private int selfQADeformKey;
    private int selfQATreeKey;
    private int deformNum;
    private String qaRowCode;
    private int defmTypeCode;
    private int defmCauseCode;
    private double htFrom;
    private double htTo;
    private String quadCode;
    private int extent;
    private int degreeLeanArch;
    private int azi;
    private int length;
    private int width;
    private int scuff;
    private int scrape;
    private int gouge;
    private int numLive;
    private int numDead;

    /**
     * SelfQADeform Constructor
     * @param selfQADeformKey
     * @param selfQATreeKey
     * @param deformNum
     * @param qaRowCode
     * @param defmTypeCode
     * @param defmCauseCode
     * @param htFrom
     * @param htTo
     * @param quadCode
     * @param extent
     * @param degreeLeanArch
     * @param azi
     * @param length
     * @param width
     * @param scuff
     * @param scrape
     * @param gouge
     * @param numLive
     * @param numDead
     */
    public SelfQADeform(int selfQADeformKey, int selfQATreeKey, int deformNum, String qaRowCode, int defmTypeCode, int defmCauseCode, double htFrom, double htTo, String quadCode, int extent, int degreeLeanArch, int azi, int length, int width, int scuff, int scrape, int gouge, int numLive, int numDead) {
        this.selfQADeformKey = selfQADeformKey;
        this.selfQATreeKey = selfQATreeKey;
        this.deformNum = deformNum;
        this.qaRowCode = qaRowCode;
        this.defmTypeCode = defmTypeCode;
        this.defmCauseCode = defmCauseCode;
        this.htFrom = htFrom;
        this.htTo = htTo;
        this.quadCode = quadCode;
        this.extent = extent;
        this.degreeLeanArch = degreeLeanArch;
        this.azi = azi;
        this.length = length;
        this.width = width;
        this.scuff = scuff;
        this.scrape = scrape;
        this.gouge = gouge;
        this.numLive = numLive;
        this.numDead = numDead;
    }

    public int getSelfQADeformKey() {
        return selfQADeformKey;
    }

    public void setSelfQADeformKey(int selfQADeformKey) {
        this.selfQADeformKey = selfQADeformKey;
    }

    public int getSelfQATreeKey() {
        return selfQATreeKey;
    }

    public void setSelfQATreeKey(int selfQATreeKey) {
        this.selfQATreeKey = selfQATreeKey;
    }

    public int getDeformNum() {
        return deformNum;
    }

    public void setDeformNum(int deformNum) {
        this.deformNum = deformNum;
    }

    public String getQaRowCode() {
        return qaRowCode;
    }

    public void setQaRowCode(String qaRowCode) {
        this.qaRowCode = qaRowCode;
    }

    public int getDefmTypeCode() {
        return defmTypeCode;
    }

    public void setDefmTypeCode(int defmTypeCode) {
        this.defmTypeCode = defmTypeCode;
    }

    public int getDefmCauseCode() {
        return defmCauseCode;
    }

    public void setDefmCauseCode(int defmCauseCode) {
        this.defmCauseCode = defmCauseCode;
    }

    public double getHtFrom() {
        return htFrom;
    }

    public void setHtFrom(double htFrom) {
        this.htFrom = htFrom;
    }

    public double getHtTo() {
        return htTo;
    }

    public void setHtTo(double htTo) {
        this.htTo = htTo;
    }

    public String getQuadCode() {
        return quadCode;
    }

    public void setQuadCode(String quadCode) {
        this.quadCode = quadCode;
    }

    public int getExtent() {
        return extent;
    }

    public void setExtent(int extent) {
        this.extent = extent;
    }

    public int getDegreeLeanArch() {
        return degreeLeanArch;
    }

    public void setDegreeLeanArch(int degreeLeanArch) {
        this.degreeLeanArch = degreeLeanArch;
    }

    public int getAzi() {
        return azi;
    }

    public void setAzi(int azi) {
        this.azi = azi;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getScuff() {
        return scuff;
    }

    public void setScuff(int scuff) {
        this.scuff = scuff;
    }

    public int getScrape() {
        return scrape;
    }

    public void setScrape(int scrape) {
        this.scrape = scrape;
    }

    public int getGouge() {
        return gouge;
    }

    public void setGouge(int gouge) {
        this.gouge = gouge;
    }

    public int getNumLive() {
        return numLive;
    }

    public void setNumLive(int numLive) {
        this.numLive = numLive;
    }

    public int getNumDead() {
        return numDead;
    }

    public void setNumDead(int numDead) {
        this.numDead = numDead;
    }
}
