package gydatainput.models.note;

public class NotePlot {
    private int notePlotKey;
    private int plotKey;
    private String plotStatusCode; // char(1)
    private String plotStatusExpln; // varchar(250)

    public int getNotePlotKey() {
        return notePlotKey;
    }

    public void setNotePlotKey(int notePlotKey) {
        this.notePlotKey = notePlotKey;
    }

    public int getPlotKey() {
        return plotKey;
    }

    public void setPlotKey(int plotKey) {
        this.plotKey = plotKey;
    }

    public String getPlotStatusCode() {
        return plotStatusCode;
    }

    public void setPlotStatusCode(String plotStatusCode) {
        this.plotStatusCode = plotStatusCode;
    }

    public String getPlotStatusExpln() {
        return plotStatusExpln;
    }

    public void setPlotStatusExpln(String plotStatusExpln) {
        this.plotStatusExpln = plotStatusExpln;
    }

    public String getDeclination() {
        return declination;
    }

    public void setDeclination(String declination) {
        this.declination = declination;
    }

    private String declination; // char(3)

    public NotePlot(int notePlotKey, String plotStatusCode, String plotStatusExpln, String declination) {
        this.notePlotKey = notePlotKey;
        this.plotStatusCode = plotStatusCode;
        this.plotStatusExpln = plotStatusExpln;
        this.declination = declination;
    }
}
