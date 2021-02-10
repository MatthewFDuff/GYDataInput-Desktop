package gydatainput.models.soilsample;

import gydatainput.models.soilhorizon.SoilHor;

public class SoilSample {
    private int soilSampleKey;
    private int plotKey;
    private String siteCondCode;
    private int soilSampleTypeCode;
    private boolean stratified;
    private String porePtrnCode;
    private String substrateType;
    private int depthToMottles;
    private int depthToDistinctMottles;
    private int depthToProminentMottles;
    private int depthToGley;
    private int depthToBedrock;
    private int depthToCarb;
    private int moistRegimeDepthClassCode;
    private String moistRegimeCode;
    private int rootDepth;
    private int depthToRootRest;
    private int depthToWaterTable;
    private int depthToImpCourseFrag;
    private int probSiteClassCode;
    private boolean seepage;
    private int drainClassCode;
    private int hfCode;
    private int soilTypeCode;
    private int elcMethodCode;

    private SoilDepMode[] soilDepModes;
    private SoilPhoto[] soilPhotos;
    private SoilHor[] soilHors;

    /**
     * Soil Sample Constructor
     * @param soilSampleKey
     * @param plotKey
     * @param siteCondCode
     * @param soilSampleTypeCode
     * @param stratified
     * @param porePtrnCode
     * @param substrateType
     * @param depthToMottles
     * @param depthToDistinctMottles
     * @param depthToProminentMottles
     * @param depthToGley
     * @param depthToBedrock
     * @param depthToCarb
     * @param moistRegimeDepthClassCode
     * @param moistRegimeCode
     * @param rootDepth
     * @param depthToRootRest
     * @param depthToWaterTable
     * @param depthToImpCourseFrag
     * @param probSiteClassCode
     * @param seepage
     * @param drainClassCode
     * @param hfCode
     * @param soilTypeCode
     * @param elcMethodCode
     */
    public SoilSample(int soilSampleKey, int plotKey, String siteCondCode, int soilSampleTypeCode, boolean stratified, String porePtrnCode, String substrateType, int depthToMottles, int depthToDistinctMottles, int depthToProminentMottles, int depthToGley, int depthToBedrock, int depthToCarb, int moistRegimeDepthClassCode, String moistRegimeCode, int rootDepth, int depthToRootRest, int depthToWaterTable, int depthToImpCourseFrag, int probSiteClassCode, boolean seepage, int drainClassCode, int hfCode, int soilTypeCode, int elcMethodCode) {
        this.soilSampleKey = soilSampleKey;
        this.plotKey = plotKey;
        this.siteCondCode = siteCondCode;
        this.soilSampleTypeCode = soilSampleTypeCode;
        this.stratified = stratified;
        this.porePtrnCode = porePtrnCode;
        this.substrateType = substrateType;
        this.depthToMottles = depthToMottles;
        this.depthToDistinctMottles = depthToDistinctMottles;
        this.depthToProminentMottles = depthToProminentMottles;
        this.depthToGley = depthToGley;
        this.depthToBedrock = depthToBedrock;
        this.depthToCarb = depthToCarb;
        this.moistRegimeDepthClassCode = moistRegimeDepthClassCode;
        this.moistRegimeCode = moistRegimeCode;
        this.rootDepth = rootDepth;
        this.depthToRootRest = depthToRootRest;
        this.depthToWaterTable = depthToWaterTable;
        this.depthToImpCourseFrag = depthToImpCourseFrag;
        this.probSiteClassCode = probSiteClassCode;
        this.seepage = seepage;
        this.drainClassCode = drainClassCode;
        this.hfCode = hfCode;
        this.soilTypeCode = soilTypeCode;
        this.elcMethodCode = elcMethodCode;
    }

    public SoilHor[] getSoilHors() {
        return soilHors;
    }

    public void setSoilHors(SoilHor[] soilHors) {
        this.soilHors = soilHors;
    }

    public SoilDepMode[] getSoilDepModes() {
        return soilDepModes;
    }

    public void setSoilDepModes(SoilDepMode[] soilDepModes) {
        this.soilDepModes = soilDepModes;
    }

    public SoilPhoto[] getSoilPhotos() {
        return soilPhotos;
    }

    public void setSoilPhotos(SoilPhoto[] soilPhotos) {
        this.soilPhotos = soilPhotos;
    }

    public int getSoilSampleKey() {
        return soilSampleKey;
    }

    public void setSoilSampleKey(int soilSampleKey) {
        this.soilSampleKey = soilSampleKey;
    }

    public int getPlotKey() {
        return plotKey;
    }

    public void setPlotKey(int plotKey) {
        this.plotKey = plotKey;
    }

    public String getSiteCondCode() {
        return siteCondCode;
    }

    public void setSiteCondCode(String siteCondCode) {
        this.siteCondCode = siteCondCode;
    }

    public int getSoilSampleTypeCode() {
        return soilSampleTypeCode;
    }

    public void setSoilSampleTypeCode(int soilSampleTypeCode) {
        this.soilSampleTypeCode = soilSampleTypeCode;
    }

    public boolean isStratified() {
        return stratified;
    }

    public void setStratified(boolean stratified) {
        this.stratified = stratified;
    }

    public String getPorePtrnCode() {
        return porePtrnCode;
    }

    public void setPorePtrnCode(String porePtrnCode) {
        this.porePtrnCode = porePtrnCode;
    }

    public String getSubstrateType() {
        return substrateType;
    }

    public void setSubstrateType(String substrateType) {
        this.substrateType = substrateType;
    }

    public int getDepthToMottles() {
        return depthToMottles;
    }

    public void setDepthToMottles(int depthToMottles) {
        this.depthToMottles = depthToMottles;
    }

    public int getDepthToDistinctMottles() {
        return depthToDistinctMottles;
    }

    public void setDepthToDistinctMottles(int depthToDistinctMottles) {
        this.depthToDistinctMottles = depthToDistinctMottles;
    }

    public int getDepthToProminentMottles() {
        return depthToProminentMottles;
    }

    public void setDepthToProminentMottles(int depthToProminentMottles) {
        this.depthToProminentMottles = depthToProminentMottles;
    }

    public int getDepthToGley() {
        return depthToGley;
    }

    public void setDepthToGley(int depthToGley) {
        this.depthToGley = depthToGley;
    }

    public int getDepthToBedrock() {
        return depthToBedrock;
    }

    public void setDepthToBedrock(int depthToBedrock) {
        this.depthToBedrock = depthToBedrock;
    }

    public int getDepthToCarb() {
        return depthToCarb;
    }

    public void setDepthToCarb(int depthToCarb) {
        this.depthToCarb = depthToCarb;
    }

    public int getMoistRegimeDepthClassCode() {
        return moistRegimeDepthClassCode;
    }

    public void setMoistRegimeDepthClassCode(int moistRegimeDepthClassCode) {
        this.moistRegimeDepthClassCode = moistRegimeDepthClassCode;
    }

    public String getMoistRegimeCode() {
        return moistRegimeCode;
    }

    public void setMoistRegimeCode(String moistRegimeCode) {
        this.moistRegimeCode = moistRegimeCode;
    }

    public int getRootDepth() {
        return rootDepth;
    }

    public void setRootDepth(int rootDepth) {
        this.rootDepth = rootDepth;
    }

    public int getDepthToRootRest() {
        return depthToRootRest;
    }

    public void setDepthToRootRest(int depthToRootRest) {
        this.depthToRootRest = depthToRootRest;
    }

    public int getDepthToWaterTable() {
        return depthToWaterTable;
    }

    public void setDepthToWaterTable(int depthToWaterTable) {
        this.depthToWaterTable = depthToWaterTable;
    }

    public int getDepthToImpCourseFrag() {
        return depthToImpCourseFrag;
    }

    public void setDepthToImpCourseFrag(int depthToImpCourseFrag) {
        this.depthToImpCourseFrag = depthToImpCourseFrag;
    }

    public int getProbSiteClassCode() {
        return probSiteClassCode;
    }

    public void setProbSiteClassCode(int probSiteClassCode) {
        this.probSiteClassCode = probSiteClassCode;
    }

    public boolean isSeepage() {
        return seepage;
    }

    public void setSeepage(boolean seepage) {
        this.seepage = seepage;
    }

    public int getDrainClassCode() {
        return drainClassCode;
    }

    public void setDrainClassCode(int drainClassCode) {
        this.drainClassCode = drainClassCode;
    }

    public int getHfCode() {
        return hfCode;
    }

    public void setHfCode(int hfCode) {
        this.hfCode = hfCode;
    }

    public int getSoilTypeCode() {
        return soilTypeCode;
    }

    public void setSoilTypeCode(int soilTypeCode) {
        this.soilTypeCode = soilTypeCode;
    }

    public int getElcMethodCode() {
        return elcMethodCode;
    }

    public void setElcMethodCode(int elcMethodCode) {
        this.elcMethodCode = elcMethodCode;
    }
}
