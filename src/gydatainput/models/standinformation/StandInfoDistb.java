package gydatainput.models.standinformation;

public class StandInfoDistb {
    private int standInfoDistbKey;
    private int distbTypeCode;
    private int distbYear;
    private int infoSourceCode;
    private int pctAffected;
    private int pctMort;

    public int getStandInfoDistbKey() {
        return standInfoDistbKey;
    }

    public void setStandInfoDistbKey(int standInfoDistbKey) {
        this.standInfoDistbKey = standInfoDistbKey;
    }

    public int getDistbTypeCode() {
        return distbTypeCode;
    }

    public void setDistbTypeCode(int distbTypeCode) {
        this.distbTypeCode = distbTypeCode;
    }

    public int getDistbYear() {
        return distbYear;
    }

    public void setDistbYear(int distbYear) {
        this.distbYear = distbYear;
    }

    public int getInfoSourceCode() {
        return infoSourceCode;
    }

    public void setInfoSourceCode(int infoSourceCode) {
        this.infoSourceCode = infoSourceCode;
    }

    public int getPctAffected() {
        return pctAffected;
    }

    public void setPctAffected(int pctAffected) {
        this.pctAffected = pctAffected;
    }

    public int getPctMort() {
        return pctMort;
    }

    public void setPctMort(int pctMort) {
        this.pctMort = pctMort;
    }

    public StandInfoDistb(int standInfoDistbKey, int distbTypeCode, int distbYear, int infoSourceCode, int pctAffected, int pctMort) {
        this.standInfoDistbKey = standInfoDistbKey;
        this.distbTypeCode = distbTypeCode;
        this.distbYear = distbYear;
        this.infoSourceCode = infoSourceCode;
        this.pctAffected = pctAffected;
        this.pctMort = pctMort;
    }
}
