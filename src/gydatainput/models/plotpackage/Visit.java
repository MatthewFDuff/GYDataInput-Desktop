package gydatainput.models.plotpackage;

import gydatainput.models.age.AgeHeader;
import gydatainput.models.downwoodydebris.DWDHeader;
import gydatainput.models.height.HtHeader;
import gydatainput.models.mortality.MortHeader;
import gydatainput.models.photo.PhotoHeader;
import gydatainput.models.plotmapping.PlotMapHeader;
import gydatainput.models.selfqa.SelfQAHeader;
import gydatainput.models.selfqa.SelfQAHt;
import gydatainput.models.soilsitetemporal.SoilEcositeHeader;
import gydatainput.models.soilsitetemporal.SoilHeader;
import gydatainput.models.standinformation.StandInfoHeader;
import gydatainput.models.stocking.StkgHeader;
import gydatainput.models.tree.TreeHeader;
import gydatainput.models.vegetation.VegHeader;

public class Visit {
    private int visitKey;
    private int visitTypeCode;
    private int fieldSeasonYear;
    private int manualCode;

    private StandInfoHeader standInfoHeader;
    private PhotoHeader photoHeader;
    private VegHeader vegHeader;
    private TreeHeader treeHeader;
    private HtHeader htHeader;
    private DWDHeader dwdHeader;
    private PlotMapHeader plotMapHeader;
    private StkgHeader stkgHeader;
    private MortHeader mortHeader;
    private AgeHeader ageHeader;
    private SoilEcositeHeader soilEcositeHeader;
    private SoilHeader soilHeader;
    private SelfQAHeader selfQAHeader;

    public SelfQAHeader getSelfQAHeader() {
        return selfQAHeader;
    }

    public void setSelfQAHeader(SelfQAHeader selfQAHeader) {
        this.selfQAHeader = selfQAHeader;
    }

    public SoilHeader getSoilHeader() {
        return soilHeader;
    }

    public void setSoilHeader(SoilHeader soilHeader) {
        this.soilHeader = soilHeader;
    }

    public SoilEcositeHeader getSoilEcositeHeader() {
        return soilEcositeHeader;
    }

    public void setSoilEcositeHeader(SoilEcositeHeader soilEcositeHeader) {
        this.soilEcositeHeader = soilEcositeHeader;
    }

    public AgeHeader getAgeHeader() {
        return ageHeader;
    }

    public void setAgeHeader(AgeHeader ageHeader) {
        this.ageHeader = ageHeader;
    }

    public MortHeader getMortHeader() {
        return mortHeader;
    }

    public void setMortHeader(MortHeader mortHeader) {
        this.mortHeader = mortHeader;
    }

    public StkgHeader getStkgHeader() {
        return stkgHeader;
    }

    public void setStkgHeader(StkgHeader stkgHeader) {
        this.stkgHeader = stkgHeader;
    }

    public DWDHeader getDwdHeader() {
        return dwdHeader;
    }

    public void setDwdHeader(DWDHeader dwdHeader) {
        this.dwdHeader = dwdHeader;
    }

    public PlotMapHeader getPlotMapHeader() {
        return plotMapHeader;
    }

    public void setPlotMapHeader(PlotMapHeader plotMapHeader) {
        this.plotMapHeader = plotMapHeader;
    }

    public DWDHeader getDWDHeader() {
        return dwdHeader;
    }

    public void setDWDHeader(DWDHeader dwdHeader) {
        this.dwdHeader = dwdHeader;
    }

    public HtHeader getHtHeader() {
        return htHeader;
    }

    public void setHtHeader(HtHeader htHeader) {
        this.htHeader = htHeader;
    }

    public TreeHeader getTreeHeader() {
        return treeHeader;
    }

    public void setTreeHeader(TreeHeader treeHeader) {
        this.treeHeader = treeHeader;
    }

    public VegHeader getVegHeader() {
        return vegHeader;
    }

    public void setVegHeader(VegHeader vegHeader) {
        this.vegHeader = vegHeader;
    }

    public PhotoHeader getPhotoHeader() {
        return photoHeader;
    }

    public void setPhotoHeader(PhotoHeader photoHeader) {
        this.photoHeader = photoHeader;
    }

    public StandInfoHeader getStandInfoHeader() {
        return standInfoHeader;
    }

    public void setStandInfoHeader(StandInfoHeader standInfoHeader) {
        this.standInfoHeader = standInfoHeader;
    }

    public Visit(int visitKey, int visitTypeCode, int fieldSeasonYear, int manualCode) {
        this.visitKey = visitKey;
        this.visitTypeCode = visitTypeCode;
        this.fieldSeasonYear = fieldSeasonYear;
        this.manualCode = manualCode;
    }

    public int getVisitKey() {
        return visitKey;
    }

    public void setVisitKey(int visitKey) {
        this.visitKey = visitKey;
    }

    public int getVisitTypeCode() {
        return visitTypeCode;
    }

    public void setVisitTypeCode(int visitTypeCode) {
        this.visitTypeCode = visitTypeCode;
    }

    public int getFieldSeasonYear() {
        return fieldSeasonYear;
    }

    public void setFieldSeasonYear(int fieldSeasonYear) {
        this.fieldSeasonYear = fieldSeasonYear;
    }

    public int getManualCode() {
        return manualCode;
    }

    public void setManualCode(int manualCode) {
        this.manualCode = manualCode;
    }
}
