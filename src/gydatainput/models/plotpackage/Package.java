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
import java.util.Iterator;

public class Package extends Table {
    private Plot plot;
    private ArrayList<Visit> visit;
    private JSONObject importJSON;

    private SpecGYHeader specGYHeader;

    // TODO: Remove when all uploading code is placed in database helper
    public JSONObject getImportJSON() {
        return importJSON;
    }

    public Package() {
    }

    public Package(JSONObject json, boolean isImport) {
        super(json, isImport);

        this.importJSON = json;

//        JSONArray fields = (JSONArray) json.get("fields");
//        this.setFields(fields);

        // Plot
        // Get the plot from the plot package JSON
        JSONObject plot = (JSONObject) json.get("tblPlot");
        // Get the plot fields from the plot package JSON
        JSONArray plotFields = (JSONArray) plot.get("fields");
//        System.out.println(plot);
//        System.out.println(plotFields);
        this.plot = new Plot(plot, true); // Create the new Plot instance
        //this.plot.setFields(plotFields); // Set the Plot fields variable.

        // Visits
        ArrayList<Visit> visits = new ArrayList<>();
        JSONArray visitsJSON = (JSONArray) json.get("tblVisit");
        Iterator<JSONObject> iterator = visitsJSON.iterator();
        while (iterator.hasNext()) {
            JSONObject visitJSON = iterator.next();
            Visit visit = new Visit(visitJSON, true);
            visits.add(visit);
        }
        this.visit = visits;
    }

    public Package(JSONArray fields) {
        super(fields);

        int plotKey = (int) this.getFromFields("PlotKey");
        setKey((int) this.getFromFields("PackageKey"));
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
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Add the Plot to the Package JSON
        json.put("tblPlot", plot.getJSON());

        // Create a JSON array to hold all Visit data.
        JSONArray visitJSON = new JSONArray();
        // Iterate through each Visit in the Package.
        visit.forEach((v) -> {
            // Add the Visit to the JSON array.
            visitJSON.add(v.getJSON());
        });

        // Add the Visit array to the Package JSON
        json.put("tblVisit", visitJSON);

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
