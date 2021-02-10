package gydatainput.models.selfqa;

public class SelfQATree {
    private int selfQATreeKey;
    private int selfQAHeaderKey;
    private int treeMsrKey;
    private String qaRowCode;
    private String tagTypeCode;
    private int specCode;
    private String treeOriginCode;
    private int postNum;
    private double dist;
    private int azi;
    private String treeStatusCode;
    private double htToDBH;
    private double dbh;
    private String qualClass2Code;
    private int liveCrownRatio;
    private String crownClassCode;
    private int crownPosnCode;
    private int crownLight;
    private boolean htTree;
    private boolean htTreeCircled;
    private int decayClass;
    private double ocularLength;
    private int mortCauseCode;
    private boolean brokenTop;
    private int crownCondCode;
    private int barkRetentionCode;
    private int woodCondCode;
    private String prescripCode;

    private SelfQADeform[] selfQADeforms;

    /**
     * SelfQATree Constructor
     * @param selfQATreeKey
     * @param selfQAHeaderKey
     * @param treeMsrKey
     * @param qaRowCode
     * @param tagTypeCode
     * @param specCode
     * @param treeOriginCode
     * @param postNum
     * @param dist
     * @param azi
     * @param treeStatusCode
     * @param htToDBH
     * @param dbh
     * @param qualClass2Code
     * @param liveCrownRatio
     * @param crownClassCode
     * @param crownPosnCode
     * @param crownLight
     * @param htTree
     * @param htTreeCircled
     * @param decayClass
     * @param ocularLength
     * @param mortCauseCode
     * @param brokenTop
     * @param crownCondCode
     * @param barkRetentionCode
     * @param woodCondCode
     * @param prescripCode
     */
    public SelfQATree(int selfQATreeKey, int selfQAHeaderKey, int treeMsrKey, String qaRowCode, String tagTypeCode, int specCode, String treeOriginCode, int postNum, double dist, int azi, String treeStatusCode, double htToDBH, double dbh, String qualClass2Code, int liveCrownRatio, String crownClassCode, int crownPosnCode, int crownLight, boolean htTree, boolean htTreeCircled, int decayClass, double ocularLength, int mortCauseCode, boolean brokenTop, int crownCondCode, int barkRetentionCode, int woodCondCode, String prescripCode) {
        this.selfQATreeKey = selfQATreeKey;
        this.selfQAHeaderKey = selfQAHeaderKey;
        this.treeMsrKey = treeMsrKey;
        this.qaRowCode = qaRowCode;
        this.tagTypeCode = tagTypeCode;
        this.specCode = specCode;
        this.treeOriginCode = treeOriginCode;
        this.postNum = postNum;
        this.dist = dist;
        this.azi = azi;
        this.treeStatusCode = treeStatusCode;
        this.htToDBH = htToDBH;
        this.dbh = dbh;
        this.qualClass2Code = qualClass2Code;
        this.liveCrownRatio = liveCrownRatio;
        this.crownClassCode = crownClassCode;
        this.crownPosnCode = crownPosnCode;
        this.crownLight = crownLight;
        this.htTree = htTree;
        this.htTreeCircled = htTreeCircled;
        this.decayClass = decayClass;
        this.ocularLength = ocularLength;
        this.mortCauseCode = mortCauseCode;
        this.brokenTop = brokenTop;
        this.crownCondCode = crownCondCode;
        this.barkRetentionCode = barkRetentionCode;
        this.woodCondCode = woodCondCode;
        this.prescripCode = prescripCode;
    }

    public SelfQADeform[] getSelfQADeforms() {
        return selfQADeforms;
    }

    public void setSelfQADeforms(SelfQADeform[] selfQADeforms) {
        this.selfQADeforms = selfQADeforms;
    }

    public int getSelfQATreeKey() {
        return selfQATreeKey;
    }

    public void setSelfQATreeKey(int selfQATreeKey) {
        this.selfQATreeKey = selfQATreeKey;
    }

    public int getSelfQAHeaderKey() {
        return selfQAHeaderKey;
    }

    public void setSelfQAHeaderKey(int selfQAHeaderKey) {
        this.selfQAHeaderKey = selfQAHeaderKey;
    }

    public int getTreeMsrKey() {
        return treeMsrKey;
    }

    public void setTreeMsrKey(int treeMsrKey) {
        this.treeMsrKey = treeMsrKey;
    }

    public String getQaRowCode() {
        return qaRowCode;
    }

    public void setQaRowCode(String qaRowCode) {
        this.qaRowCode = qaRowCode;
    }

    public String getTagTypeCode() {
        return tagTypeCode;
    }

    public void setTagTypeCode(String tagTypeCode) {
        this.tagTypeCode = tagTypeCode;
    }

    public int getSpecCode() {
        return specCode;
    }

    public void setSpecCode(int specCode) {
        this.specCode = specCode;
    }

    public String getTreeOriginCode() {
        return treeOriginCode;
    }

    public void setTreeOriginCode(String treeOriginCode) {
        this.treeOriginCode = treeOriginCode;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public int getAzi() {
        return azi;
    }

    public void setAzi(int azi) {
        this.azi = azi;
    }

    public String getTreeStatusCode() {
        return treeStatusCode;
    }

    public void setTreeStatusCode(String treeStatusCode) {
        this.treeStatusCode = treeStatusCode;
    }

    public double getHtToDBH() {
        return htToDBH;
    }

    public void setHtToDBH(double htToDBH) {
        this.htToDBH = htToDBH;
    }

    public double getDbh() {
        return dbh;
    }

    public void setDbh(double dbh) {
        this.dbh = dbh;
    }

    public String getQualClass2Code() {
        return qualClass2Code;
    }

    public void setQualClass2Code(String qualClass2Code) {
        this.qualClass2Code = qualClass2Code;
    }

    public int getLiveCrownRatio() {
        return liveCrownRatio;
    }

    public void setLiveCrownRatio(int liveCrownRatio) {
        this.liveCrownRatio = liveCrownRatio;
    }

    public String getCrownClassCode() {
        return crownClassCode;
    }

    public void setCrownClassCode(String crownClassCode) {
        this.crownClassCode = crownClassCode;
    }

    public int getCrownPosnCode() {
        return crownPosnCode;
    }

    public void setCrownPosnCode(int crownPosnCode) {
        this.crownPosnCode = crownPosnCode;
    }

    public int getCrownLight() {
        return crownLight;
    }

    public void setCrownLight(int crownLight) {
        this.crownLight = crownLight;
    }

    public boolean isHtTree() {
        return htTree;
    }

    public void setHtTree(boolean htTree) {
        this.htTree = htTree;
    }

    public boolean isHtTreeCircled() {
        return htTreeCircled;
    }

    public void setHtTreeCircled(boolean htTreeCircled) {
        this.htTreeCircled = htTreeCircled;
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

    public int getMortCauseCode() {
        return mortCauseCode;
    }

    public void setMortCauseCode(int mortCauseCode) {
        this.mortCauseCode = mortCauseCode;
    }

    public boolean isBrokenTop() {
        return brokenTop;
    }

    public void setBrokenTop(boolean brokenTop) {
        this.brokenTop = brokenTop;
    }

    public int getCrownCondCode() {
        return crownCondCode;
    }

    public void setCrownCondCode(int crownCondCode) {
        this.crownCondCode = crownCondCode;
    }

    public int getBarkRetentionCode() {
        return barkRetentionCode;
    }

    public void setBarkRetentionCode(int barkRetentionCode) {
        this.barkRetentionCode = barkRetentionCode;
    }

    public int getWoodCondCode() {
        return woodCondCode;
    }

    public void setWoodCondCode(int woodCondCode) {
        this.woodCondCode = woodCondCode;
    }

    public String getPrescripCode() {
        return prescripCode;
    }

    public void setPrescripCode(String prescripCode) {
        this.prescripCode = prescripCode;
    }
}
