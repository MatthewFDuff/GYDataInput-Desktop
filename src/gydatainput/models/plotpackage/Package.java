package gydatainput.models.plotpackage;

import gydatainput.database.DatabaseHelper;

import java.sql.ResultSet;
import java.time.LocalDate;

public class Package {
    private Plot plot;
    private Visit[] visits;

    private int packageKey;
    private int plotKey;
    private int startYear;
    private int approachCode;
    private boolean coOpMethod;

    public Package(int packageKey, int plotKey, int startYear, int approachCode, int coOpMethod) {
        this.packageKey = packageKey;
        this.plotKey = plotKey;
        this.startYear = startYear;
        this.approachCode = approachCode;
        this.coOpMethod = (coOpMethod == 1 ? true : false); // Converts bit to boolean

        this.plot = new Plot(plotKey);
        this.visits = DatabaseHelper.getVisits(packageKey);
    }

    public String getName() {
        return this.plot.getPlotName();
    }

    public int getPackageKey() {
        return packageKey;
    }

    public void setPackageKey(int packageKey) {
        this.packageKey = packageKey;
    }

    public int getPlotKey() {
        return plotKey;
    }

    public void setPlotKey(int plotKey) {
        this.plotKey = plotKey;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getApproachCode() {
        return approachCode;
    }

    public void setApproachCode(int approachCode) {
        this.approachCode = approachCode;
    }

    public boolean isCoOpMethod() {
        return coOpMethod;
    }

    public void setCoOpMethod(boolean coOpMethod) {
        this.coOpMethod = coOpMethod;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public Visit[] getVisits() {
        return visits;
    }

    public void setVisits(Visit[] visits) {
        this.visits = visits;
    }

}
