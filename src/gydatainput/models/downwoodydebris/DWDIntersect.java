package gydatainput.models.downwoodydebris;

public class DWDIntersect {
    private int dwdIntersectKey;
    private int dwdLineKey;
    private int dwdKey;
    private double dia;
    private int tiltAngle;

    public int getDwdIntersectKey() {
        return dwdIntersectKey;
    }

    public void setDwdIntersectKey(int dwdIntersectKey) {
        this.dwdIntersectKey = dwdIntersectKey;
    }

    public int getDwdLineKey() {
        return dwdLineKey;
    }

    public void setDwdLineKey(int dwdLineKey) {
        this.dwdLineKey = dwdLineKey;
    }

    public int getDwdKey() {
        return dwdKey;
    }

    public void setDwdKey(int dwdKey) {
        this.dwdKey = dwdKey;
    }

    public double getDia() {
        return dia;
    }

    public void setDia(double dia) {
        this.dia = dia;
    }

    public int getTiltAngle() {
        return tiltAngle;
    }

    public void setTiltAngle(int tiltAngle) {
        this.tiltAngle = tiltAngle;
    }

    public DWDIntersect(int dwdIntersectKey, int dwdLineKey, int dwdKey, double dia, int tiltAngle) {
        this.dwdIntersectKey = dwdIntersectKey;
        this.dwdLineKey = dwdLineKey;
        this.dwdKey = dwdKey;
        this.dia = dia;
        this.tiltAngle = tiltAngle;
    }
}
