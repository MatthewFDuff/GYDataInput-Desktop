package gydatainput.models.sitepermissions;

public class SitePermPlot {
    private int sitePermPlotKey;
    private int landAdminCode;
    private boolean consultRequired;
    private boolean grantedPerm;
    private boolean permSignature;
    private String consultRequirement;
    private String obligation;

    public SitePermPlot(int sitePermPlotKey, int landAdminCode, boolean consultRequired, boolean grantedPerm, boolean permSignature, String consultRequirement, String obligation) {
        this.sitePermPlotKey = sitePermPlotKey;
        this.landAdminCode = landAdminCode;
        this.consultRequired = consultRequired;
        this.grantedPerm = grantedPerm;
        this.permSignature = permSignature;
        this.consultRequirement = consultRequirement;
        this.obligation = obligation;
    }

    public int getSitePermPlotKey() {
        return sitePermPlotKey;
    }

    public void setSitePermPlotKey(int sitePermPlotKey) {
        this.sitePermPlotKey = sitePermPlotKey;
    }

    public int getLandAdminCode() {
        return landAdminCode;
    }

    public void setLandAdminCode(int landAdminCode) {
        this.landAdminCode = landAdminCode;
    }

    public boolean isConsultRequired() {
        return consultRequired;
    }

    public void setConsultRequired(boolean consultRequired) {
        this.consultRequired = consultRequired;
    }

    public boolean isGrantedPerm() {
        return grantedPerm;
    }

    public void setGrantedPerm(boolean grantedPerm) {
        this.grantedPerm = grantedPerm;
    }

    public boolean isPermSignature() {
        return permSignature;
    }

    public void setPermSignature(boolean permSignature) {
        this.permSignature = permSignature;
    }

    public String getConsultRequirement() {
        return consultRequirement;
    }

    public void setConsultRequirement(String consultRequirement) {
        this.consultRequirement = consultRequirement;
    }

    public String getObligation() {
        return obligation;
    }

    public void setObligation(String obligation) {
        this.obligation = obligation;
    }
}
