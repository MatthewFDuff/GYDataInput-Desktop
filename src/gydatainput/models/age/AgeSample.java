package gydatainput.models.age;

public class AgeSample {
    private int ageSampleKey;
    private int ageTreeKey;
    private int ageSampleTypeCode;
    private double baseHt;
    private double baseDia;
    private String ageSampleStatusCode;
    private double soundWoodLength;
    private int fieldAge;
    private int officeAge;
    private String confidCode;
    private int barkThickness;
    private int incre5;
    private int incre10;
    private int incre15;

    public AgeSample(int ageSampleKey, int ageSampleTypeCode, int ageTreeKey, double baseHt, double baseDia, String ageSampleStatusCode, double soundWoodLength, int fieldAge, int officeAge, String confidCode, int barkThickness, int incre5, int incre10, int incre15) {
        this.ageSampleKey = ageSampleKey;
        this.ageSampleTypeCode = ageSampleTypeCode;
        this.ageTreeKey = ageTreeKey;
        this.baseHt = baseHt;
        this.baseDia = baseDia;
        this.ageSampleStatusCode = ageSampleStatusCode;
        this.soundWoodLength = soundWoodLength;
        this.fieldAge = fieldAge;
        this.officeAge = officeAge;
        this.confidCode = confidCode;
        this.barkThickness = barkThickness;
        this.incre5 = incre5;
        this.incre10 = incre10;
        this.incre15 = incre15;
    }

    public int getAgeSampleTypeCode() {
        return ageSampleTypeCode;
    }

    public void setAgeSampleTypeCode(int ageSampleTypeCode) {
        this.ageSampleTypeCode = ageSampleTypeCode;
    }

    public int getAgeSampleKey() {
        return ageSampleKey;
    }

    public void setAgeSampleKey(int ageSampleKey) {
        this.ageSampleKey = ageSampleKey;
    }

    public int getAgeTreeKey() {
        return ageTreeKey;
    }

    public void setAgeTreeKey(int ageTreeKey) {
        this.ageTreeKey = ageTreeKey;
    }

    public double getBaseHt() {
        return baseHt;
    }

    public void setBaseHt(double baseHt) {
        this.baseHt = baseHt;
    }

    public double getBaseDia() {
        return baseDia;
    }

    public void setBaseDia(double baseDia) {
        this.baseDia = baseDia;
    }

    public String getAgeSampleStatusCode() {
        return ageSampleStatusCode;
    }

    public void setAgeSampleStatusCode(String ageSampleStatusCode) {
        this.ageSampleStatusCode = ageSampleStatusCode;
    }

    public double getSoundWoodLength() {
        return soundWoodLength;
    }

    public void setSoundWoodLength(double soundWoodLength) {
        this.soundWoodLength = soundWoodLength;
    }

    public int getFieldAge() {
        return fieldAge;
    }

    public void setFieldAge(int fieldAge) {
        this.fieldAge = fieldAge;
    }

    public int getOfficeAge() {
        return officeAge;
    }

    public void setOfficeAge(int officeAge) {
        this.officeAge = officeAge;
    }

    public String getConfidCode() {
        return confidCode;
    }

    public void setConfidCode(String confidCode) {
        this.confidCode = confidCode;
    }

    public int getBarkThickness() {
        return barkThickness;
    }

    public void setBarkThickness(int barkThickness) {
        this.barkThickness = barkThickness;
    }

    public int getIncre5() {
        return incre5;
    }

    public void setIncre5(int incre5) {
        this.incre5 = incre5;
    }

    public int getIncre10() {
        return incre10;
    }

    public void setIncre10(int incre10) {
        this.incre10 = incre10;
    }

    public int getIncre15() {
        return incre15;
    }

    public void setIncre15(int incre15) {
        this.incre15 = incre15;
    }
}
