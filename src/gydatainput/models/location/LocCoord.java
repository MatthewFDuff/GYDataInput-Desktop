package gydatainput.models.location;

public class LocCoord {
    private int locCoordKey;
    private int coordTypeCode;
    private int postNum;
    private int easting;
    private int northing;
    private int datum;
    private String gpsClassCode;
    private boolean accurate;

    public int getLocCoordKey() {
        return locCoordKey;
    }

    public void setLocCoordKey(int locCoordKey) {
        this.locCoordKey = locCoordKey;
    }

    public int getCoordTypeCode() {
        return coordTypeCode;
    }

    public void setCoordTypeCode(int coordTypeCode) {
        this.coordTypeCode = coordTypeCode;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public int getEasting() {
        return easting;
    }

    public void setEasting(int easting) {
        this.easting = easting;
    }

    public int getNorthing() {
        return northing;
    }

    public void setNorthing(int northing) {
        this.northing = northing;
    }

    public int getDatum() {
        return datum;
    }

    public void setDatum(int datum) {
        this.datum = datum;
    }

    public String getGpsClassCode() {
        return gpsClassCode;
    }

    public void setGpsClassCode(String gpsClassCode) {
        this.gpsClassCode = gpsClassCode;
    }

    public boolean isAccurate() {
        return accurate;
    }

    public void setAccurate(boolean accurate) {
        this.accurate = accurate;
    }

    public LocCoord(int locCoordKey, int coordTypeCode, int postNum, int easting, int northing, int datum, String gpsClassCode, boolean accurate) {
        this.locCoordKey = locCoordKey;
        this.coordTypeCode = coordTypeCode;
        this.postNum = postNum;
        this.easting = easting;
        this.northing = northing;
        this.datum = datum;
        this.gpsClassCode = gpsClassCode;
        this.accurate = accurate;
    }
}
