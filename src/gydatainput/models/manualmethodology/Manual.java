package gydatainput.models.manualmethodology;

public class Manual {
    private int manualCode;
    private String manualName;
    private int manualYear;
    private boolean manualCurrent;
    private int methVegCoverCode;
    private int methRegenCode;
    private int methShrubSpecCode;
    private int methCrwnClsrCode;
    private int methSoilSampleCode;
    private double liveMinDBH;
    private double deadMinDBH;
    private double deadMinHt;
    private double mortMinDBH;
    private double mortMinLength;

    public Manual() {
    }

    /**
     * Manual Constructor
     * @param manualCode
     * @param manualName
     * @param manualYear
     * @param manualCurrent
     * @param methVegCoverCode
     * @param methRegenCode
     * @param methShrubSpecCode
     * @param methCrwnClsrCode
     * @param methSoilSampleCode
     * @param liveMinDBH
     * @param deadMinDBH
     * @param deadMinHt
     * @param mortMinDBH
     * @param mortMinLength
     */
    public Manual(int manualCode, String manualName, int manualYear, boolean manualCurrent, int methVegCoverCode, int methRegenCode, int methShrubSpecCode, int methCrwnClsrCode, int methSoilSampleCode, double liveMinDBH, double deadMinDBH, double deadMinHt, double mortMinDBH, double mortMinLength) {
        this.manualCode = manualCode;
        this.manualName = manualName;
        this.manualYear = manualYear;
        this.manualCurrent = manualCurrent;
        this.methVegCoverCode = methVegCoverCode;
        this.methRegenCode = methRegenCode;
        this.methShrubSpecCode = methShrubSpecCode;
        this.methCrwnClsrCode = methCrwnClsrCode;
        this.methSoilSampleCode = methSoilSampleCode;
        this.liveMinDBH = liveMinDBH;
        this.deadMinDBH = deadMinDBH;
        this.deadMinHt = deadMinHt;
        this.mortMinDBH = mortMinDBH;
        this.mortMinLength = mortMinLength;
    }

    public int getManualCode() {
        return manualCode;
    }

    public void setManualCode(int manualCode) {
        this.manualCode = manualCode;
    }

    public String getManualName() {
        return manualName;
    }

    public void setManualName(String manualName) {
        this.manualName = manualName;
    }

    public int getManualYear() {
        return manualYear;
    }

    public void setManualYear(int manualYear) {
        this.manualYear = manualYear;
    }

    public boolean isManualCurrent() {
        return manualCurrent;
    }

    public void setManualCurrent(boolean manualCurrent) {
        this.manualCurrent = manualCurrent;
    }

    public int getMethVegCoverCode() {
        return methVegCoverCode;
    }

    public void setMethVegCoverCode(int methVegCoverCode) {
        this.methVegCoverCode = methVegCoverCode;
    }

    public int getMethRegenCode() {
        return methRegenCode;
    }

    public void setMethRegenCode(int methRegenCode) {
        this.methRegenCode = methRegenCode;
    }

    public int getMethShrubSpecCode() {
        return methShrubSpecCode;
    }

    public void setMethShrubSpecCode(int methShrubSpecCode) {
        this.methShrubSpecCode = methShrubSpecCode;
    }

    public int getMethCrwnClsrCode() {
        return methCrwnClsrCode;
    }

    public void setMethCrwnClsrCode(int methCrwnClsrCode) {
        this.methCrwnClsrCode = methCrwnClsrCode;
    }

    public int getMethSoilSampleCode() {
        return methSoilSampleCode;
    }

    public void setMethSoilSampleCode(int methSoilSampleCode) {
        this.methSoilSampleCode = methSoilSampleCode;
    }

    public double getLiveMinDBH() {
        return liveMinDBH;
    }

    public void setLiveMinDBH(double liveMinDBH) {
        this.liveMinDBH = liveMinDBH;
    }

    public double getDeadMinDBH() {
        return deadMinDBH;
    }

    public void setDeadMinDBH(double deadMinDBH) {
        this.deadMinDBH = deadMinDBH;
    }

    public double getDeadMinHt() {
        return deadMinHt;
    }

    public void setDeadMinHt(double deadMinHt) {
        this.deadMinHt = deadMinHt;
    }

    public double getMortMinDBH() {
        return mortMinDBH;
    }

    public void setMortMinDBH(double mortMinDBH) {
        this.mortMinDBH = mortMinDBH;
    }

    public double getMortMinLength() {
        return mortMinLength;
    }

    public void setMortMinLength(double mortMinLength) {
        this.mortMinLength = mortMinLength;
    }
}
