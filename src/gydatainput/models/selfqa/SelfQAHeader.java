package gydatainput.models.selfqa;

public class SelfQAHeader {
    private int selfQAHeaderKey;
    private int visitKey;
    private String msrDate;

    private SelfQAHt[] selfQAHts;
    private SelfQATree[] selfQATrees;

    /**
     * SelfQAHeader Constructor
     * @param selfQAHeaderKey
     * @param visitKey
     * @param msrDate
     */
    public SelfQAHeader(int selfQAHeaderKey, int visitKey, String msrDate) {
        this.selfQAHeaderKey = selfQAHeaderKey;
        this.visitKey = visitKey;
        this.msrDate = msrDate;
    }

    public SelfQAHt[] getSelfQAHts() {
        return selfQAHts;
    }

    public void setSelfQAHts(SelfQAHt[] selfQAHts) {
        this.selfQAHts = selfQAHts;
    }

    public SelfQATree[] getSelfQATrees() {
        return selfQATrees;
    }

    public void setSelfQATrees(SelfQATree[] selfQATrees) {
        this.selfQATrees = selfQATrees;
    }

    public int getSelfQAHeaderKey() {
        return selfQAHeaderKey;
    }

    public void setSelfQAHeaderKey(int selfQAHeaderKey) {
        this.selfQAHeaderKey = selfQAHeaderKey;
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
}
