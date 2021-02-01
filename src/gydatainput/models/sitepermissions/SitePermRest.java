package gydatainput.models.sitepermissions;

public class SitePermRest {
    private int sitePermRestKey;
    private int restTypeCode;
    private String restDetail;

    public int getSitePermRestKey() {
        return sitePermRestKey;
    }

    public void setSitePermRestKey(int sitePermRestKey) {
        this.sitePermRestKey = sitePermRestKey;
    }

    public int getRestTypeCode() {
        return restTypeCode;
    }

    public void setRestTypeCode(int restTypeCode) {
        this.restTypeCode = restTypeCode;
    }

    public String getRestDetail() {
        return restDetail;
    }

    public void setRestDetail(String restDetail) {
        this.restDetail = restDetail;
    }

    public SitePermRest(int sitePermRestKey, int restTypeCode, String restDetail) {
        this.sitePermRestKey = sitePermRestKey;
        this.restTypeCode = restTypeCode;
        this.restDetail = restDetail;
    }
}
