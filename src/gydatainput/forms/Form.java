package gydatainput.forms;

/**
 * The Form class contains information and functions which all forms require, such as the plot name.
 *
 * @author Matthew Duff
 * @version 0.1
 * @since 2020-12-01
 * */
public class Form {

    private boolean completed; // Whether or not the form has been completed by the field crew.
    private String plot; // Plot Name
    private String growthPlotNumber; // Growth Plot Number
    private String date; // Date

    public boolean isCompleted() {
        return completed;
    }

    /* Getters and Setters */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getGrowthPlotNumber() {
        return growthPlotNumber;
    }

    public void setGrowthPlotNumber(String growthPlotNumber) {
        this.growthPlotNumber = growthPlotNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
