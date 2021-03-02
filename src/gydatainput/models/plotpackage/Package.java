package gydatainput.models.plotpackage;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.specialist.SpecGYHeader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Package extends Table {
    private Plot plot;
    private ArrayList<Visit> visit;

    private SpecGYHeader specGYHeader;

    public Package(JSONObject fields) {
        super(fields);

        int plotKey = (int) this.getFields().get("PlotKey");
        setKey((int) this.getFields().get("PackageKey"));
        setKeyName("PackageKey");

        try {
            this.plot = DatabaseHelper.getData(plotKey, "PlotKey", "tblPlot", Plot.class);
            this.visit = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblVisit", Visit.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all plot package data from the database for the plot package.
     */
    public void loadPackage() throws SQLException {
        // Get all data for the package.
        specGYHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblSpecGYHeader", SpecGYHeader.class);

        // Get all plot data.
        plot.retrieveData();

        // Get all data for each visit.
        visit.forEach((visit) -> {
            visit.retrieveData();
        });
    }

    public JSONObject getJSON() {
        // Create a new JSON object to hold all Package data.
        JSONObject json = this.getFields();

        // Add the Plot to the Package JSON
        json.put("Plot", plot.getJSON());

        // Create a JSON array to hold all Visit data.
        JSONArray visitJSON = new JSONArray();
        // Iterate through each Visit in the Package.
        visit.forEach((v) -> {
            // Add the Visit to the JSON array.
            visitJSON.add(v.getJSON());
        });

        // Add the Visit array to the Package JSON
        json.put("Visit", visitJSON);

        return json;
    }

    // GETTERS
    public Plot getPlot() {
        return plot;
    }

    public ArrayList<Visit> getVisits() {
        return visit;
    }
}
