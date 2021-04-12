package gydatainput.models.plotpackage;

import gydatainput.database.DatabaseController;
import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.specialist.SpecGYHeader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Package extends Table {
    private Plot plot;
    private ArrayList<Visit> visit;
    private SpecGYHeader specGYHeader;

    private JSONObject importJSON; // Only gets used for imported files.

    // TODO: Remove when all uploading code is placed in database helper
    public JSONObject getImportJSON() {
        return importJSON;
    }

    public Package() {
    }

    public Package(JSONObject json, boolean isImport) {
        super(json, isImport);

        this.importJSON = json; // TODO Remove when json mapping is complete.

        // Plot
        this.plot = getObjectFromJSON(json, "tblPlot", Plot.class);

        // Visits
        this.visit = getArrayFromJSON(json, "tblVisit", Visit.class);

        // SpecGYHeader
        this.specGYHeader = getObjectFromJSON(json, "tblSpecGYHeader", SpecGYHeader.class);
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

    @Override
    public void upload() throws SQLException {
        Savepoint save = DatabaseController.getConnection().setSavepoint();
        System.out.println("Begin transaction.");
        try {
            super.upload();

            uploadObject(plot);
            uploadArray(visit);
            uploadObject(specGYHeader);

            // Successfully uploaded plot package.
            DatabaseController.getConnection().commit();
            System.out.println("End Transaction: Commit");
        } catch (SQLException e) {
            DatabaseController.getConnection().rollback(save);
            System.out.println("End Transaction: Rollback");
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
