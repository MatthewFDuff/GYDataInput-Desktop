package gydatainput.models.deformity;

public class TreeDefm {
    private int treeDefmKey;
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

    public int getTreeDefmKey() {
        return treeDefmKey;
    }

    public void setTreeDefmKey(int treeDefmKey) {
        this.treeDefmKey = treeDefmKey;
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

    public TreeDefm(int treeDefmKey, int defmTypeCode, int defmCauseCode, double htFrom, double htTo, String quadCode, int extent, int degreeLeanArch, int azi, int length, int width, int scuff, int scrape, int gouge, int numLive, int numDead) {
        this.treeDefmKey = treeDefmKey;
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
}
