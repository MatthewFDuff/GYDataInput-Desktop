package gydatainput.models.standinformation;

public class StandInfoTreat {
    private int standInfoTreatKey;
    private int treatTypeCode;
    private int treatYear;
    private int infoSourceCode;

    public int getStandInfoTreatKey() {
        return standInfoTreatKey;
    }

    public void setStandInfoTreatKey(int standInfoTreatKey) {
        this.standInfoTreatKey = standInfoTreatKey;
    }

    public int getTreatTypeCode() {
        return treatTypeCode;
    }

    public void setTreatTypeCode(int treatTypeCode) {
        this.treatTypeCode = treatTypeCode;
    }

    public int getTreatYear() {
        return treatYear;
    }

    public void setTreatYear(int treatYear) {
        this.treatYear = treatYear;
    }

    public int getInfoSourceCode() {
        return infoSourceCode;
    }

    public void setInfoSourceCode(int infoSourceCode) {
        this.infoSourceCode = infoSourceCode;
    }

    public StandInfoTreat(int standInfoTreatKey, int treatTypeCode, int treatYear, int infoSourceCode) {
        this.standInfoTreatKey = standInfoTreatKey;
        this.treatTypeCode = treatTypeCode;
        this.treatYear = treatYear;
        this.infoSourceCode = infoSourceCode;
    }
}
