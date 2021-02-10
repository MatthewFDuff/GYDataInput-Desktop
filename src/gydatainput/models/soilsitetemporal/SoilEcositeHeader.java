package gydatainput.models.soilsitetemporal;

public class SoilEcositeHeader {
    private int soilEcositeHeaderKey;
    private int visitKey;
    private String msrDate;

    private SoilEcosite[] soilEcosites;

    /**
     * SoilEcositeHeader Constructor
     * @param soilEcositeHeaderKey
     * @param visitKey
     * @param msrDate
     */
    public SoilEcositeHeader(int soilEcositeHeaderKey, int visitKey, String msrDate) {
        this.soilEcositeHeaderKey = soilEcositeHeaderKey;
        this.visitKey = visitKey;
        this.msrDate = msrDate;
    }

    public SoilEcosite[] getSoilEcosites() {
        return soilEcosites;
    }

    public void setSoilEcosites(SoilEcosite[] soilEcosites) {
        this.soilEcosites = soilEcosites;
    }

    public int getSoilEcositeHeaderKey() {
        return soilEcositeHeaderKey;
    }

    public void setSoilEcositeHeaderKey(int soilEcositeHeaderKey) {
        this.soilEcositeHeaderKey = soilEcositeHeaderKey;
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
