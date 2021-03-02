package gydatainput.models.plotpackage;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.location.LocCoord;
import gydatainput.models.location.LocPlot;
import gydatainput.models.location.PlotAccess;
import gydatainput.models.note.Note;
import gydatainput.models.note.NoteFixup;
import gydatainput.models.note.NotePlot;
import gydatainput.models.plotmapping.PlotMapGrowthPlot;
import gydatainput.models.plotmapping.PlotMapMort;
import gydatainput.models.sitepermissions.SitePermPlot;
import gydatainput.models.sitepermissions.SitePermRest;
import gydatainput.models.soilsample.SoilSample;
import gydatainput.models.soilsitemacromesomicro.SoilGrowthPlot;
import gydatainput.models.soilsitemacromesomicro.SoilPlot;
import gydatainput.models.specialist.SpecAssoc;
import gydatainput.models.standinformation.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.ArrayList;

public class Plot extends Table {
    // Note
    private ArrayList<Note> note;
    private ArrayList<NoteFixup> noteFixup;
    private NotePlot notePlot;

    // Site Permissions
    private SitePermRest sitePermRest;
    private SitePermPlot sitePermPlot;

    // Location
    private LocPlot locPlot;
    private ArrayList<PlotAccess> plotAccess;
    private ArrayList<LocCoord> locCoord;

    // Stand Information
    private StandInfoPlot standInfoPlot;
    private ArrayList<StandInfoDistb> standInfoDistb;
    private ArrayList<StandInfoTreat> standInfoTreat;
    private ArrayList<StandInfoCompr> standInfoCompr;

    // Plot Mapping
    private PlotMapMort plotMapMort;
    private ArrayList<PlotMapGrowthPlot> plotMapGrowthPlot;

    // Soil Sample
    private ArrayList<SoilSample> soilSample;

    // Soil Site Macro Meso Micro
    private SoilPlot soilPlot;
    private ArrayList<SoilGrowthPlot> soilGrowthPlot;

    // Specialist
    private ArrayList<SpecAssoc> specAssoc;

    public Plot() {
    }

    public Plot(JSONObject fields) {
        super(fields);
    }

    public void retrieveData() throws SQLException {
        this.note = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblNote", Note.class);
        this.noteFixup = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblNoteFixup", NoteFixup.class);
        this.notePlot = DatabaseHelper.getData(getKey(), getKeyName(), "tblNotePlot", NotePlot.class);
        this.sitePermRest = DatabaseHelper.getData(getKey(), getKeyName(), "tblSitePermRest", SitePermRest.class);
        this.sitePermPlot = DatabaseHelper.getData(getKey(), getKeyName(), "tblSitePermPlot", SitePermPlot.class);
        this.locPlot = DatabaseHelper.getData(getKey(), getKeyName(), "tblLocPlot", LocPlot.class);
        this.plotAccess = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblPlotAccess", PlotAccess.class);
        this.locCoord = DatabaseHelper.getObjects(getKey(),getKeyName(), "tblLocCoord", LocCoord.class);
        this.standInfoPlot = DatabaseHelper.getData(getKey(), getKeyName(), "tblStandInfoPlot", StandInfoPlot.class);
        this.standInfoDistb = DatabaseHelper.getObjects(getKey(),getKeyName(), "tblStandInfoDistb", StandInfoDistb.class);
        this.standInfoTreat = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblStandInfoTreat", StandInfoTreat.class);
        this.standInfoCompr = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblStandInfoCompr", StandInfoCompr.class);
        this.plotMapMort = DatabaseHelper.getData(getKey(), getKeyName(), "tblPlotMapMort", PlotMapMort.class);
        this.plotMapGrowthPlot = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblPlotMapGrowthPlot", PlotMapGrowthPlot.class);
        this.soilSample = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilSample", SoilSample.class);
        this.soilPlot = DatabaseHelper.getData(getKey(), getKeyName(), "tblSoilPlot", SoilPlot.class);
        this.soilGrowthPlot = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilGrowthPlot", SoilGrowthPlot.class);
        this.specAssoc = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSpecAssoc", SpecAssoc.class);
    }

    public JSONObject getJSON() {
        JSONObject json = this.getFields();

        // Note
        if (!note.isEmpty()) {
            JSONArray noteJSON = new JSONArray();
            note.forEach((n) -> {
                noteJSON.add(n.getFields());
            });
            json.put("Note", noteJSON);
        }

        // NoteFixup
        if (!noteFixup.isEmpty()) {
            JSONArray noteFixupJSON = new JSONArray();
            note.forEach((n) -> {
                noteFixupJSON.add(n.getFields());
            });
            json.put("NoteFixup", noteFixupJSON);
        }

        // Note Plot
        if (notePlot != null) {
            json.put("NotePlot", notePlot.getFields());
        }

        // Site Perm Rest
        if (sitePermRest != null) {
            json.put("SitePermRest", sitePermRest.getFields());
        }

        // Site Perm Plot
        if (sitePermPlot != null) {
            json.put("SitePermPlot", sitePermPlot.getFields());
        }

        // Loc Plot
        if (locPlot != null) {
            json.put("LocPlot", locPlot.getFields());
        }

        // Plot Access
        if (!plotAccess.isEmpty()) {
            JSONArray plotAccessJSON = new JSONArray();
            note.forEach((n) -> {
                plotAccessJSON.add(n.getFields());
            });
            json.put("PlotAccess", plotAccessJSON);
        }

        // Loc Coord
        if (!locCoord.isEmpty()) {
            JSONArray locCoordJSON = new JSONArray();
            locCoord.forEach((n) -> {
                locCoordJSON.add(n.getFields());
            });
            json.put("LocCoord", locCoordJSON);
        }

        // Stand Info Plot
        if (standInfoPlot != null) {
            json.put("StandInfoPlot", standInfoPlot.getFields());
        }

        // Stand Info Distb
        if (!standInfoDistb.isEmpty()) {
            JSONArray standInfoDistbJSON = new JSONArray();
            standInfoDistb.forEach((n) -> {
                standInfoDistbJSON.add(n.getFields());
            });
            json.put("StandInfoDistb", standInfoDistbJSON);
        }

        // Stand Info Treat
        if (!standInfoTreat.isEmpty()) {
            JSONArray standInfoTreatJSON = new JSONArray();
            standInfoTreat.forEach((n) -> {
                standInfoTreatJSON.add(n.getFields());
            });
            json.put("StandInfoTreat", standInfoTreatJSON);
        }

        // Stand Info Compr
        if (!standInfoCompr.isEmpty()) {
            JSONArray standInfoComprJSON = new JSONArray();
            standInfoCompr.forEach((n) -> {
                standInfoComprJSON.add(n.getFields());
            });
            json.put("StandInfoCompr", standInfoComprJSON);
        }

        // Plot Map Mort
        if (plotMapMort != null) {
            json.put("PlotMapMort", plotMapMort.getFields());
        }

        // Plot Map Growth Plot
        if (!plotMapGrowthPlot.isEmpty()) {
            JSONArray plotMapGrowthPlotJSON = new JSONArray();
            plotMapGrowthPlot.forEach((n) -> {
                plotMapGrowthPlotJSON.add(n.getJSON());
            });
            json.put("PlotMapGrowthPlot", plotMapGrowthPlotJSON);
        }

        // Soil Sample
        if (!soilSample.isEmpty()) {
            JSONArray soilSampleJSON = new JSONArray();
            soilSample.forEach((n) -> {
                soilSampleJSON.add(n.getJSON());
            });
            json.put("SoilSample", soilSampleJSON);
        }

        // Soil Plot
        if (soilPlot != null) {
            json.put("SoilPlot", soilPlot.getFields());
        }

        // Soil Growth Plot
        if (!soilGrowthPlot.isEmpty()) {
            JSONArray soilGrowthPlotJSON = new JSONArray();
            soilGrowthPlot.forEach((n) -> {
                soilGrowthPlotJSON.add(n.getFields());
            });
            json.put("SoilGrowthPlot", soilGrowthPlotJSON);
        }

        // Spec Assoc
        if (!specAssoc.isEmpty()) {
            JSONArray specAssocJSON = new JSONArray();
            specAssoc.forEach((n) -> {
                specAssocJSON.add(n.getFields());
            });
            json.put("SpecAssoc", specAssocJSON);
        }

        return json;
    }
}
