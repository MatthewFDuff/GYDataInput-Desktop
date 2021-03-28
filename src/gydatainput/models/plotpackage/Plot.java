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
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Note
        JSONArray noteJSON = getChildFieldArray(note);
        if(noteJSON != null) {
            json.put("tblNote", noteJSON);
        }

        // NoteFixup
        JSONArray noteFixupJSON = getChildFieldArray(noteFixup);
        if(noteFixupJSON != null) {
            json.put("tblNoteFixup", noteFixupJSON);
        }

        // Note Plot
        if (notePlot != null) {
            json.put("tblNotePlot", notePlot.getJSON());
        }

        // Site Perm Rest
        if (sitePermRest != null) {
            json.put("tblSitePermRest", sitePermRest.getJSON());
        }

        // Site Perm Plot
        if (sitePermPlot != null) {
            json.put("tblSitePermPlot", sitePermPlot.getJSON());
        }

        // Loc Plot
        if (locPlot != null) {
            json.put("tblLocPlot", locPlot.getJSON());
        }

        // Plot Access
        JSONArray plotAccessJSON = getChildFieldArray(plotAccess);
        if(plotAccessJSON != null) {
            json.put("tblPlotAccess", plotAccessJSON);
        }

        // Loc Coord
        JSONArray locCoordJSON = getChildFieldArray(locCoord);
        if(locCoordJSON != null) {
            json.put("tblLocCoord", locCoordJSON);
        }

        // Stand Info Plot
        if (standInfoPlot != null) {
            json.put("tblStandInfoPlot", standInfoPlot.getJSON());
        }

        // Stand Info Distb
        JSONArray standInfoDistbJSON = getChildFieldArray(standInfoDistb);
        if(standInfoDistbJSON != null) {
            json.put("tblStandInfoDistb", standInfoDistbJSON);
        }

        // Stand Info Treat
        JSONArray standInfoTreatJSON = getChildFieldArray(standInfoTreat);
        if(standInfoTreatJSON != null) {
            json.put("tblStandInfoTreat", standInfoTreatJSON);
        }

        // Stand Info Compr
        JSONArray standInfoComprJSON = getChildFieldArray(standInfoCompr);
        if(standInfoComprJSON != null) {
            json.put("tblStandInfoCompr", standInfoComprJSON);
        }

        // Plot Map Mort
        if (plotMapMort != null) {
            json.put("tblPlotMapMort", plotMapMort.getJSON());
        }

        // Plot Map Growth Plot
        JSONArray plotMapGrowthPlotJSON = getChildFieldArrayWithJSON(plotMapGrowthPlot);
        if(plotMapGrowthPlotJSON != null) {
            json.put("tblPlotMapGrowthPlot", plotMapGrowthPlotJSON);
        }

        // Soil Sample
        JSONArray soilSampleJSON = getChildFieldArrayWithJSON(soilSample);
        if(soilSampleJSON != null) {
            json.put("tblSoilSample", soilSampleJSON);
        }

        // Soil Plot
        if (soilPlot != null) {
            json.put("tblSoilPlot", soilPlot.getJSON());
        }

        // Soil Growth Plot
        JSONArray soilGrowthPlotJSON = getChildFieldArray(soilGrowthPlot);
        if(soilGrowthPlotJSON != null) {
            json.put("tblSoilGrowthPlot", soilGrowthPlotJSON);
        }

        // Spec Assoc
        JSONArray specAssocJSON = getChildFieldArray(specAssoc);
        if(specAssocJSON != null) {
            json.put("tblSpecAssoc", specAssocJSON);
        }

        return json;
    }
}
