package gydatainput.models.age;

public class AgeHeader {
    private int ageHeaderKey;
    private int visitKey;
    private String msrDate;
    private boolean residCompnt;

    private AgeTree[] ageTrees;

    public AgeHeader(int ageHeaderKey, int visitKey, String msrDate, boolean residCompnt) {
        this.ageHeaderKey = ageHeaderKey;
        this.visitKey = visitKey;
        this.msrDate = msrDate;
        this.residCompnt = residCompnt;
    }

    public AgeTree[] getAgeTrees() {
        return ageTrees;
    }

    public void setAgeTrees(AgeTree[] ageTrees) {
        this.ageTrees = ageTrees;
    }

    public int getAgeHeaderKey() {
        return ageHeaderKey;
    }

    public void setAgeHeaderKey(int ageHeaderKey) {
        this.ageHeaderKey = ageHeaderKey;
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

    public boolean isResidCompnt() {
        return residCompnt;
    }

    public void setResidCompnt(boolean residCompnt) {
        this.residCompnt = residCompnt;
    }
}
