package gydatainput.models.plotpackage;

import gydatainput.models.downwoodydebris.DWDHeader;
import gydatainput.models.height.HtHeader;
import gydatainput.models.photo.PhotoHeader;
import gydatainput.models.standinformation.StandInfoHeader;
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
