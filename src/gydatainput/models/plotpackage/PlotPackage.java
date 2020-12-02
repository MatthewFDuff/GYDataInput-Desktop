package gydatainput.models.plotpackage;

import java.time.LocalDate;

public class PlotPackage {

    private String name;
    private String date;

    private int manual;
    private int approach;
    private int visitType;

    private boolean isCompleted;

    public PlotPackage(String packageName, String packageDate, int packageManual, int packageApproach, int packageVisitType, boolean packageCompleted) {
        this.name = packageName;
        this.date = packageDate;
        this.manual = packageManual;
        this.approach = packageApproach;
        this.visitType = packageVisitType;
        this.isCompleted = packageCompleted;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManual() {
        return manual;
    }

    public void setManual(int manual) {
        this.manual = manual;
    }

    public int getApproach() {
        return approach;
    }

    public void setApproach(int approach) {
        this.approach = approach;
    }

    public int getVisitType() {
        return visitType;
    }

    public void setVisitType(int visitType) {
        this.visitType = visitType;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
