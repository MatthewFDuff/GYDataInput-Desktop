package gydatainput.models.soilsitetemporal;

public class SoilForestFloor {
    private int soilForestFloorKey;
    private int soilHeaderKey;
    private int growthPlotNum;
    private double litterThickness;
    private double fibricThickness;
    private double humusThickness;

    /**
     * SoilForestFloor Constructor
     * @param soilForestFloorKey
     * @param soilHeaderKey
     * @param growthPlotNum
     * @param litterThickness
     * @param fibricThickness
     * @param humusThickness
     */
    public SoilForestFloor(int soilForestFloorKey, int soilHeaderKey, int growthPlotNum, double litterThickness, double fibricThickness, double humusThickness) {
        this.soilForestFloorKey = soilForestFloorKey;
        this.soilHeaderKey = soilHeaderKey;
        this.growthPlotNum = growthPlotNum;
        this.litterThickness = litterThickness;
        this.fibricThickness = fibricThickness;
        this.humusThickness = humusThickness;
    }

    public int getSoilForestFloorKey() {
        return soilForestFloorKey;
    }

    public void setSoilForestFloorKey(int soilForestFloorKey) {
        this.soilForestFloorKey = soilForestFloorKey;
    }

    public int getSoilHeaderKey() {
        return soilHeaderKey;
    }

    public void setSoilHeaderKey(int soilHeaderKey) {
        this.soilHeaderKey = soilHeaderKey;
    }

    public int getGrowthPlotNum() {
        return growthPlotNum;
    }

    public void setGrowthPlotNum(int growthPlotNum) {
        this.growthPlotNum = growthPlotNum;
    }

    public double getLitterThickness() {
        return litterThickness;
    }

    public void setLitterThickness(double litterThickness) {
        this.litterThickness = litterThickness;
    }

    public double getFibricThickness() {
        return fibricThickness;
    }

    public void setFibricThickness(double fibricThickness) {
        this.fibricThickness = fibricThickness;
    }

    public double getHumusThickness() {
        return humusThickness;
    }

    public void setHumusThickness(double humusThickness) {
        this.humusThickness = humusThickness;
    }
}
