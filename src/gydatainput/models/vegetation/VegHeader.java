package gydatainput.models.vegetation;

public class VegHeader {
    private int vegHeaderKey;
    private String msrDate;

    public int getVegHeaderKey() {
        return vegHeaderKey;
    }

    public void setVegHeaderKey(int vegHeaderKey) {
        this.vegHeaderKey = vegHeaderKey;
    }

    public String getMsrDate() {
        return msrDate;
    }

    public void setMsrDate(String msrDate) {
        this.msrDate = msrDate;
    }

    public VegVType[] getVegVTypes() {
        return vegVTypes;
    }

    public void setVegVTypes(VegVType[] vegVTypes) {
        this.vegVTypes = vegVTypes;
    }

    public VegPlot[] getVegPlots() {
        return vegPlots;
    }

    public void setVegPlots(VegPlot[] vegPlots) {
        this.vegPlots = vegPlots;
    }

    public VegHeader(int vegHeaderKey, String msrDate) {
        this.vegHeaderKey = vegHeaderKey;
        this.msrDate = msrDate;
    }

    private VegVType[] vegVTypes;
    private VegPlot[] vegPlots;
}
