package gydatainput.models.soilsitetemporal;

public class SoilHeader {
    private int soilHeaderKey;
    private int visitKey;
    private String msrDate;

    private SoilForestFloor[] soilForestFloors;
    private SoilGroundCover[] soilGroundCovers;

    /**
     * SoilHeader Constructor
     * @param soilHeaderKey
     * @param visitKey
     * @param msrDate
     */
    public SoilHeader(int soilHeaderKey, int visitKey, String msrDate) {
        this.soilHeaderKey = soilHeaderKey;
        this.visitKey = visitKey;
        this.msrDate = msrDate;
    }

    public SoilForestFloor[] getSoilForestFloors() {
        return soilForestFloors;
    }

    public void setSoilForestFloors(SoilForestFloor[] soilForestFloors) {
        this.soilForestFloors = soilForestFloors;
    }

    public SoilGroundCover[] getSoilGroundCovers() {
        return soilGroundCovers;
    }

    public void setSoilGroundCovers(SoilGroundCover[] soilGroundCovers) {
        this.soilGroundCovers = soilGroundCovers;
    }

    public int getSoilHeaderKey() {
        return soilHeaderKey;
    }

    public void setSoilHeaderKey(int soilHeaderKey) {
        this.soilHeaderKey = soilHeaderKey;
    }

    public int getVisitKey() {
        return visitKey;
    }

    public void setVisitKey(int visitKey) {
        this.visitKey = visitKey;
    }

    public String getMsrDate() {
        return msrDate;
    }

    public void setMsrDate(String msrDate) {
        this.msrDate = msrDate;
    }
}
