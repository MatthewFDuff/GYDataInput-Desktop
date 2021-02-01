package gydatainput.models.mortality;

public class MortHeader {
    private int mortHeaderKey;
    private int visitKey;
    private String msrDate;
    private int crownClsr;
    private double radius;
    private double length;
    private double width;

    private MortTreeMsr[] mortTreeMsrs;

    public MortHeader(int mortHeaderKey, int visitKey, String msrDate, int crownClsr, double radius, double length, double width) {
        this.mortHeaderKey = mortHeaderKey;
        this.visitKey = visitKey;
        this.msrDate = msrDate;
        this.crownClsr = crownClsr;
        this.radius = radius;
        this.length = length;
        this.width = width;
    }

    public MortTreeMsr[] getMortTreeMsrs() {
        return mortTreeMsrs;
    }

    public void setMortTreeMsrs(MortTreeMsr[] mortTreeMsrs) {
        this.mortTreeMsrs = mortTreeMsrs;
    }

    public int getMortHeaderKey() {
        return mortHeaderKey;
    }

    public void setMortHeaderKey(int mortHeaderKey) {
        this.mortHeaderKey = mortHeaderKey;
    }

    public int getVisitKey() {
        return visitKey;
    }

    public void setVisitKey(int visitKey) {
        this.visitKey = visitKey;
    }

    public String getMsrDate() {
        return msrDate;
    }

    public void setMsrDate(String msrDate) {
        this.msrDate = msrDate;
    }

    public int getCrownClsr() {
        return crownClsr;
    }

    public void setCrownClsr(int crownClsr) {
        this.crownClsr = crownClsr;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
