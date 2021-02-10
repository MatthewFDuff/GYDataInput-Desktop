package gydatainput.models.soilhorizon;

public class SoilHor {
    private int soilHorKey;
    private int soilSampleKey;
    private int soilHorNum;
    private double depthFrom;
    private double depthTo;
    private String horRoman;
    private int horBaseModCode;
    private int horSub;
    private int organicDecompCode;
    private String mnrlTxtrCode;
    private String porePtrnCode;
    private int structCode;
    private String colour;
    private String mottleColour;
    private int pctGravel;
    private int pctCobble;
    private int pctStone;
    private int coarseFragmentCode;
    private String structGradeCode;
    private String structKindCode;

    /**
     * SoilHor Constructor
     * @param soilHorKey
     * @param soilSampleKey
     * @param soilHorNum
     * @param depthFrom
     * @param depthTo
     * @param horRoman
     * @param horBaseModCode
     * @param horSub
     * @param organicDecompCode
     * @param mnrlTxtrCode
     * @param porePtrnCode
     * @param structCode
     * @param colour
     * @param mottleColour
     * @param pctGravel
     * @param pctCobble
     * @param pctStone
     * @param coarseFragmentCode
     * @param structGradeCode
     * @param structKindCode
     */
    public SoilHor(int soilHorKey, int soilSampleKey, int soilHorNum, double depthFrom, double depthTo, String horRoman, int horBaseModCode, int horSub, int organicDecompCode, String mnrlTxtrCode, String porePtrnCode, int structCode, String colour, String mottleColour, int pctGravel, int pctCobble, int pctStone, int coarseFragmentCode, String structGradeCode, String structKindCode) {
        this.soilHorKey = soilHorKey;
        this.soilSampleKey = soilSampleKey;
        this.soilHorNum = soilHorNum;
        this.depthFrom = depthFrom;
        this.depthTo = depthTo;
        this.horRoman = horRoman;
        this.horBaseModCode = horBaseModCode;
        this.horSub = horSub;
        this.organicDecompCode = organicDecompCode;
        this.mnrlTxtrCode = mnrlTxtrCode;
        this.porePtrnCode = porePtrnCode;
        this.structCode = structCode;
        this.colour = colour;
        this.mottleColour = mottleColour;
        this.pctGravel = pctGravel;
        this.pctCobble = pctCobble;
        this.pctStone = pctStone;
        this.coarseFragmentCode = coarseFragmentCode;
        this.structGradeCode = structGradeCode;
        this.structKindCode = structKindCode;
    }

    public int getSoilHorKey() {
        return soilHorKey;
    }

    public void setSoilHorKey(int soilHorKey) {
        this.soilHorKey = soilHorKey;
    }

    public int getSoilSampleKey() {
        return soilSampleKey;
    }

    public void setSoilSampleKey(int soilSampleKey) {
        this.soilSampleKey = soilSampleKey;
    }

    public int getSoilHorNum() {
        return soilHorNum;
    }

    public void setSoilHorNum(int soilHorNum) {
        this.soilHorNum = soilHorNum;
    }

    public double getDepthFrom() {
        return depthFrom;
    }

    public void setDepthFrom(double depthFrom) {
        this.depthFrom = depthFrom;
    }

    public double getDepthTo() {
        return depthTo;
    }

    public void setDepthTo(double depthTo) {
        this.depthTo = depthTo;
    }

    public String getHorRoman() {
        return horRoman;
    }

    public void setHorRoman(String horRoman) {
        this.horRoman = horRoman;
    }

    public int getHorBaseModCode() {
        return horBaseModCode;
    }

    public void setHorBaseModCode(int horBaseModCode) {
        this.horBaseModCode = horBaseModCode;
    }

    public int getHorSub() {
        return horSub;
    }

    public void setHorSub(int horSub) {
        this.horSub = horSub;
    }

    public int getOrganicDecompCode() {
        return organicDecompCode;
    }

    public void setOrganicDecompCode(int organicDecompCode) {
        this.organicDecompCode = organicDecompCode;
    }

    public String getMnrlTxtrCode() {
        return mnrlTxtrCode;
    }

    public void setMnrlTxtrCode(String mnrlTxtrCode) {
        this.mnrlTxtrCode = mnrlTxtrCode;
    }

    public String getPorePtrnCode() {
        return porePtrnCode;
    }

    public void setPorePtrnCode(String porePtrnCode) {
        this.porePtrnCode = porePtrnCode;
    }

    public int getStructCode() {
        return structCode;
    }

    public void setStructCode(int structCode) {
        this.structCode = structCode;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getMottleColour() {
        return mottleColour;
    }

    public void setMottleColour(String mottleColour) {
        this.mottleColour = mottleColour;
    }

    public int getPctGravel() {
        return pctGravel;
    }

    public void setPctGravel(int pctGravel) {
        this.pctGravel = pctGravel;
    }

    public int getPctCobble() {
        return pctCobble;
    }

    public void setPctCobble(int pctCobble) {
        this.pctCobble = pctCobble;
    }

    public int getPctStone() {
        return pctStone;
    }

    public void setPctStone(int pctStone) {
        this.pctStone = pctStone;
    }

    public int getCoarseFragmentCode() {
        return coarseFragmentCode;
    }

    public void setCoarseFragmentCode(int coarseFragmentCode) {
        this.coarseFragmentCode = coarseFragmentCode;
    }

    public String getStructGradeCode() {
        return structGradeCode;
    }

    public void setStructGradeCode(String structGradeCode) {
        this.structGradeCode = structGradeCode;
    }

    public String getStructKindCode() {
        return structKindCode;
    }

    public void setStructKindCode(String structKindCode) {
        this.structKindCode = structKindCode;
    }
}
