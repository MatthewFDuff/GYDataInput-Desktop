package gydatainput.models.soilsample;

public class SoilDepMode {
    private int soilDepModeKey;
    private int soilSampleKey;
    private String depModeCode;
    private int rank;

    /**
     * SoilDepMode Constructor
     * @param soilDepModeKey
     * @param soilSampleKey
     * @param depModeCode
     * @param rank
     */
    public SoilDepMode(int soilDepModeKey, int soilSampleKey, String depModeCode, int rank) {
        this.soilDepModeKey = soilDepModeKey;
        this.soilSampleKey = soilSampleKey;
        this.depModeCode = depModeCode;
        this.rank = rank;
    }

    public int getSoilDepModeKey() {
        return soilDepModeKey;
    }

    public void setSoilDepModeKey(int soilDepModeKey) {
        this.soilDepModeKey = soilDepModeKey;
    }

    public int getSoilSampleKey() {
        return soilSampleKey;
    }

    public void setSoilSampleKey(int soilSampleKey) {
        this.soilSampleKey = soilSampleKey;
    }

    public String getDepModeCode() {
        return depModeCode;
    }

    public void setDepModeCode(String depModeCode) {
        this.depModeCode = depModeCode;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
