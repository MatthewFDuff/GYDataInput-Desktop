package gydatainput.models.plotpackage;

import java.time.LocalDate;

public class PlotPackage {

    private LocalDate date;
    private String name;
    private boolean isCompleted;

    public PlotPackage(LocalDate packageDate, String packageName, boolean packageCompleted) {
        this.date = packageDate;
        this.name = packageName;
        this.isCompleted = packageCompleted;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
