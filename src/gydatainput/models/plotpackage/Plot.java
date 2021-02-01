package gydatainput.models.plotpackage;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.location.LocCoord;
import gydatainput.models.location.LocPlot;
import gydatainput.models.location.PlotAccess;
import gydatainput.models.note.Note;
import gydatainput.models.note.NoteFixup;
import gydatainput.models.note.NotePlot;
import gydatainput.models.sitepermissions.SitePermPlot;
import gydatainput.models.sitepermissions.SitePermRest;
import gydatainput.models.standinformation.StandInfoCompr;
import gydatainput.models.standinformation.StandInfoDistb;
import gydatainput.models.standinformation.StandInfoPlot;
import gydatainput.models.standinformation.StandInfoTreat;

public class Plot {
    private int plotKey;
    private String plotName;
    private int datasetCode;
    private String plotAlias;
    private String adminRegionCode;
    private boolean protectionNegotiable;

    public Note[] getNotes() {
        return notes;
    }

    public void setNotes(Note[] notes) {
        this.notes = notes;
    }

    // Note
    private Note[] notes;
    private NoteFixup[] noteFixups;
    private NotePlot notePlot;

    // Site Permissions
    private SitePermRest sitePermRest;
    private SitePermPlot sitePermPlot;

    // Location
    private LocPlot locPlot;
    private PlotAccess[] plotAccesses;
    private LocCoord[] locCoords;

    // Stand Information
    private StandInfoPlot standInfoPlot;
    private StandInfoDistb[] standInfoDistb;
    private StandInfoTreat[] standInfoTreat;
    private StandInfoCompr[] standInfoCompr;

    public StandInfoPlot getStandInfoPlot() {
        return standInfoPlot;
    }

    public void setStandInfoPlot(StandInfoPlot standInfoPlot) {
        this.standInfoPlot = standInfoPlot;
    }

    public StandInfoDistb[] getStandInfoDistb() {
        return standInfoDistb;
    }

    public void setStandInfoDistb(StandInfoDistb[] standInfoDistb) {
        this.standInfoDistb = standInfoDistb;
    }

    public StandInfoTreat[] getStandInfoTreat() {
        return standInfoTreat;
    }

    public void setStandInfoTreat(StandInfoTreat[] standInfoTreat) {
        this.standInfoTreat = standInfoTreat;
    }

    public StandInfoCompr[] getStandInfoCompr() {
        return standInfoCompr;
    }

    public void setStandInfoCompr(StandInfoCompr[] standInfoCompr) {
        this.standInfoCompr = standInfoCompr;
    }

    public LocPlot getLocPlot() {
        return locPlot;
    }

    public void setLocPlot(LocPlot locPlot) {
        this.locPlot = locPlot;
    }

    public PlotAccess[] getPlotAccesses() {
        return plotAccesses;
    }

    public void setPlotAccesses(PlotAccess[] plotAccesses) {
        this.plotAccesses = plotAccesses;
    }

    public LocCoord[] getLocCoords() {
        return locCoords;
    }

    public void setLocCoords(LocCoord[] locCoords) {
        this.locCoords = locCoords;
    }

    public SitePermRest getSitePermRest() {
        return sitePermRest;
    }

    public void setSitePermRest(SitePermRest sitePermRest) {
        this.sitePermRest = sitePermRest;
    }

    public SitePermPlot getSitePermPlot() {
        return sitePermPlot;
    }

    public void setSitePermPlot(SitePermPlot sitePermPlot) {
        this.sitePermPlot = sitePermPlot;
    }

    public NoteFixup[] getNoteFixups() {
        return noteFixups;
    }

    public void setNoteFixups(NoteFixup[] noteFixups) {
        this.noteFixups = noteFixups;
    }

    public NotePlot getNotePlot() {
        return notePlot;
    }

    public void setNotePlot(NotePlot notePlot) {
        this.notePlot = notePlot;
    }

    public Plot(int plotKey) {
        DatabaseHelper.getPlotData(this, plotKey);
    }

    public int getPlotKey() {
        return plotKey;
    }

    public void setPlotKey(int plotKey) {
        this.plotKey = plotKey;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public int getDatasetCode() {
        return datasetCode;
    }

    public void setDatasetCode(int datasetCode) {
        this.datasetCode = datasetCode;
    }

    public String getPlotAlias() {
        return plotAlias;
    }

    public void setPlotAlias(String plotAlias) {
        this.plotAlias = plotAlias;
    }

    public String getAdminRegionCode() {
        return adminRegionCode;
    }

    public void setAdminRegionCode(String adminRegionCode) {
        this.adminRegionCode = adminRegionCode;
    }

    public boolean isProtectionNegotiable() {
        return protectionNegotiable;
    }

    public void setProtectionNegotiable(boolean protectionNegotiable) {
        this.protectionNegotiable = protectionNegotiable;
    }
}
