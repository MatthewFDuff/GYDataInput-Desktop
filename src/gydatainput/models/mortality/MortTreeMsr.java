package gydatainput.models.mortality;

public class MortTreeMsr {
    private int mortTreeMsrKey;
    private int mortHeaderKey;
    private int mortTreeKey;
    private String mortPosCode;
    private boolean prevAssessed;
    private int decayClass;
    private double ocularLength;
    private boolean brokenTop;

    private MortTree mortTree;

    public MortTreeMsr(int mortTreeMsrKey, int mortHeaderKey, int mortTreeKey, String mortPosCode, boolean prevAssessed, int decayClass, double ocularLength, boolean brokenTop) {
        this.mortTreeMsrKey = mortTreeMsrKey;
        this.mortHeaderKey = mortHeaderKey;
        this.mortTreeKey = mortTreeKey;
        this.mortPosCode = mortPosCode;
        this.prevAssessed = prevAssessed;
        this.decayClass = decayClass;
        this.ocularLength = ocularLength;
        this.brokenTop = brokenTop;
    }

    public MortTree getMortTree() {
        return mortTree;
    }

    public void setMortTree(MortTree mortTree) {
        this.mortTree = mortTree;
    }

    public int getMortTreeMsrKey() {
        return mortTreeMsrKey;
    }

    public void setMortTreeMsrKey(int mortTreeMsrKey) {
        this.mortTreeMsrKey = mortTreeMsrKey;
    }

    public int getMortHeaderKey() {
        return mortHeaderKey;
    }

    public void setMortHeaderKey(int mortHeaderKey) {
        this.mortHeaderKey = mortHeaderKey;
    }

    public int getMortTreeKey() {
        return mortTreeKey;
    }

    public void setMortTreeKey(int mortTreeKey) {
        this.mortTreeKey = mortTreeKey;
    }

    public String getMortPosCode() {
        return mortPosCode;
    }

    public void setMortPosCode(String mortPosCode) {
        this.mortPosCode = mortPosCode;
    }

    public boolean isPrevAssessed() {
        return prevAssessed;
    }

    public void setPrevAssessed(boolean prevAssessed) {
        this.prevAssessed = prevAssessed;
    }

    public int getDecayClass() {
        return decayClass;
    }

    public void setDecayClass(int decayClass) {
        this.decayClass = decayClass;
    }

    public double getOcularLength() {
        return ocularLength;
    }

    public void setOcularLength(double ocularLength) {
        this.ocularLength = ocularLength;
    }

    public boolean isBrokenTop() {
        return brokenTop;
    }

    public void setBrokenTop(boolean brokenTop) {
        this.brokenTop = brokenTop;
    }
}
