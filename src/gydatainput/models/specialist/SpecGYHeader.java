package gydatainput.models.specialist;

public class SpecGYHeader {
    private int specGYHeaderKey;
    private int packageKey;
    private String completedDate;
    private String mgmtCode;
    private String effectiveCanopyCode;
    private int silvSysCode;

    /**
     * SpecGYHeader Constructor
     * @param specGYHeaderKey
     * @param packageKey
     * @param completedDate
     * @param mgmtCode
     * @param effectiveCanopyCode
     * @param silvSysCode
     */
    public SpecGYHeader(int specGYHeaderKey, int packageKey, String completedDate, String mgmtCode, String effectiveCanopyCode, int silvSysCode) {
        this.specGYHeaderKey = specGYHeaderKey;
        this.packageKey = packageKey;
        this.completedDate = completedDate;
        this.mgmtCode = mgmtCode;
        this.effectiveCanopyCode = effectiveCanopyCode;
        this.silvSysCode = silvSysCode;
    }

    public int getSpecGYHeaderKey() {
        return specGYHeaderKey;
    }

    public void setSpecGYHeaderKey(int specGYHeaderKey) {
        this.specGYHeaderKey = specGYHeaderKey;
    }

    public int getPackageKey() {
        return packageKey;
    }

    public void setPackageKey(int packageKey) {
        this.packageKey = packageKey;
    }

    public String getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(String completedDate) {
        this.completedDate = completedDate;
    }

    public String getMgmtCode() {
        return mgmtCode;
    }

    public void setMgmtCode(String mgmtCode) {
        this.mgmtCode = mgmtCode;
    }

    public String getEffectiveCanopyCode() {
        return effectiveCanopyCode;
    }

    public void setEffectiveCanopyCode(String effectiveCanopyCode) {
        this.effectiveCanopyCode = effectiveCanopyCode;
    }

    public int getSilvSysCode() {
        return silvSysCode;
    }

    public void setSilvSysCode(int silvSysCode) {
        this.silvSysCode = silvSysCode;
    }
}
