package gydatainput.models.standinformation;

public class StandInfoHeader {
    private int standInfoHeaderKey;
    private String msrDate;
    private String canopyStructCode;
    private int plotUnifCode;
    private String plotUnifRationale;
    private int mainCanopyOriginCode;
    private String maturClassCode;
    private String maturClassRationale;

    public int getStandInfoHeaderKey() {
        return standInfoHeaderKey;
    }

    public void setStandInfoHeaderKey(int standInfoHeaderKey) {
        this.standInfoHeaderKey = standInfoHeaderKey;
    }

    public String getMsrDate() {
        return msrDate;
    }

    public void setMsrDate(String msrDate) {
        this.msrDate = msrDate;
    }

    public String getCanopyStructCode() {
        return canopyStructCode;
    }

    public void setCanopyStructCode(String canopyStructCode) {
        this.canopyStructCode = canopyStructCode;
    }

    public int getPlotUnifCode() {
        return plotUnifCode;
    }

    public void setPlotUnifCode(int plotUnifCode) {
        this.plotUnifCode = plotUnifCode;
    }

    public String getPlotUnifRationale() {
        return plotUnifRationale;
    }

    public void setPlotUnifRationale(String plotUnifRationale) {
        this.plotUnifRationale = plotUnifRationale;
    }

    public int getMainCanopyOriginCode() {
        return mainCanopyOriginCode;
    }

    public void setMainCanopyOriginCode(int mainCanopyOriginCode) {
        this.mainCanopyOriginCode = mainCanopyOriginCode;
    }

    public String getMaturClassCode() {
        return maturClassCode;
    }

    public void setMaturClassCode(String maturClassCode) {
        this.maturClassCode = maturClassCode;
    }

    public String getMaturClassRationale() {
        return maturClassRationale;
    }

    public void setMaturClassRationale(String maturClassRationale) {
        this.maturClassRationale = maturClassRationale;
    }

    public StandInfoHeader(int standInfoHeaderKey, String msrDate, String canopyStructCode, int plotUnifCode, String plotUnifRationale, int mainCanopyOriginCode, String maturClassCode, String maturClassRationale) {
        this.standInfoHeaderKey = standInfoHeaderKey;
        this.msrDate = msrDate;
        this.canopyStructCode = canopyStructCode;
        this.plotUnifCode = plotUnifCode;
        this.plotUnifRationale = plotUnifRationale;
        this.mainCanopyOriginCode = mainCanopyOriginCode;
        this.maturClassCode = maturClassCode;
        this.maturClassRationale = maturClassRationale;
    }
}
