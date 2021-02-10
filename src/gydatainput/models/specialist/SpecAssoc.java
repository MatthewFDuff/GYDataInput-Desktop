package gydatainput.models.specialist;

public class SpecAssoc {
    private int specAssocKey;
    private int plotKey;
    private String associatedPlotName;
    private int datasetCode;
    private int assocTypeCode;

    /**
     * SpecAssoc Constructor
     * @param specAssocKey
     * @param plotKey
     * @param associatedPlotName
     * @param datasetCode
     * @param assocTypeCode
     */
    public SpecAssoc(int specAssocKey, int plotKey, String associatedPlotName, int datasetCode, int assocTypeCode) {
        this.specAssocKey = specAssocKey;
        this.plotKey = plotKey;
        this.associatedPlotName = associatedPlotName;
        this.datasetCode = datasetCode;
        this.assocTypeCode = assocTypeCode;
    }

    public int getSpecAssocKey() {
        return specAssocKey;
    }

    public void setSpecAssocKey(int specAssocKey) {
        this.specAssocKey = specAssocKey;
    }

    public int getPlotKey() {
        return plotKey;
    }

    public void setPlotKey(int plotKey) {
        this.plotKey = plotKey;
    }

    public String getAssociatedPlotName() {
        return associatedPlotName;
    }

    public void setAssociatedPlotName(String associatedPlotName) {
        this.associatedPlotName = associatedPlotName;
    }

    public int getDatasetCode() {
        return datasetCode;
    }

    public void setDatasetCode(int datasetCode) {
        this.datasetCode = datasetCode;
    }

    public int getAssocTypeCode() {
        return assocTypeCode;
    }

    public void setAssocTypeCode(int assocTypeCode) {
        this.assocTypeCode = assocTypeCode;
    }
}
