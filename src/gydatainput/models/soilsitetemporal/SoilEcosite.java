package gydatainput.models.soilsitetemporal;

public class SoilEcosite {
    private int soilEcositeKey;
    private int soilEcositeHeaderKey;
    private String esAreaCode;
    private int esTypeCode;
    private String esTypeOptionalPart;
    private int pct;
    private int elcMethodCode;

    /**
     * SoilEcosite Constructor
     * @param soilEcositeKey
     * @param soilEcositeHeaderKey
     * @param esAreaCode
     * @param esTypeCode
     * @param esTypeOptionalPart
     * @param pct
     * @param elcMethodCode
     */
    public SoilEcosite(int soilEcositeKey, int soilEcositeHeaderKey, String esAreaCode, int esTypeCode, String esTypeOptionalPart, int pct, int elcMethodCode) {
        this.soilEcositeKey = soilEcositeKey;
        this.soilEcositeHeaderKey = soilEcositeHeaderKey;
        this.esAreaCode = esAreaCode;
        this.esTypeCode = esTypeCode;
        this.esTypeOptionalPart = esTypeOptionalPart;
        this.pct = pct;
        this.elcMethodCode = elcMethodCode;
    }

    public int getSoilEcositeKey() {
        return soilEcositeKey;
    }

    public void setSoilEcositeKey(int soilEcositeKey) {
        this.soilEcositeKey = soilEcositeKey;
    }

    public int getSoilEcositeHeaderKey() {
        return soilEcositeHeaderKey;
    }

    public void setSoilEcositeHeaderKey(int soilEcositeHeaderKey) {
        this.soilEcositeHeaderKey = soilEcositeHeaderKey;
    }

    public String getEsAreaCode() {
        return esAreaCode;
    }

    public void setEsAreaCode(String esAreaCode) {
        this.esAreaCode = esAreaCode;
    }

    public int getEsTypeCode() {
        return esTypeCode;
    }

    public void setEsTypeCode(int esTypeCode) {
        this.esTypeCode = esTypeCode;
    }

    public String getEsTypeOptionalPart() {
        return esTypeOptionalPart;
    }

    public void setEsTypeOptionalPart(String esTypeOptionalPart) {
        this.esTypeOptionalPart = esTypeOptionalPart;
    }

    public int getPct() {
        return pct;
    }

    public void setPct(int pct) {
        this.pct = pct;
    }

    public int getElcMethodCode() {
        return elcMethodCode;
    }

    public void setElcMethodCode(int elcMethodCode) {
        this.elcMethodCode = elcMethodCode;
    }
}
