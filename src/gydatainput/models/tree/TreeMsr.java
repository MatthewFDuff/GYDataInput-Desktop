package gydatainput.models.tree;

import gydatainput.models.cavity.TreeCav;
import gydatainput.models.deformity.TreeDefm;
import gydatainput.models.height.Ht;

public class TreeMsr {
    private int treeMsrKey;
    private int treeKey;
    private String treeStatusCode;
    private double htToDBH;
    private double dbh;
    private String qualClass2Code;
    private String qualClass5Code;
    private String qualClass6Code;
    private int liveCrownRatio;
    private String crownClassCode;
    private int crownPosnCode;
    private int crownLight;
    private boolean htTree;
    private boolean htTreeCircled;
    private int decayClass;
    private double ocularLength;
    private boolean brokenTop;
    private int crownCondCode;
    private int barkRetentionCode;
    private int woodCondCode;
    private String prescripCode;
    private boolean backfill;

    private Tree tree;
    private TreeMissed treeMissed;
    private TreeDefm[] treeDefms;
    private TreeCav[] treeCavs;
    private Ht ht;

    public Ht getHt() {
        return ht;
    }

    public void setHt(Ht ht) {
        this.ht = ht;
    }

    public TreeDefm[] getTreeDefms() {
        return treeDefms;
    }

    public void setTreeDefms(TreeDefm[] treeDefms) {
        this.treeDefms = treeDefms;
    }

    public TreeCav[] getTreeCavs() {
        return treeCavs;
    }

    public void setTreeCavs(TreeCav[] treeCavs) {
        this.treeCavs = treeCavs;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public TreeMissed getTreeMissed() {
        return treeMissed;
    }

    public void setTreeMissed(TreeMissed treeMissed) {
        this.treeMissed = treeMissed;
    }

    public int getTreeMsrKey() {
        return treeMsrKey;
    }

    public void setTreeMsrKey(int treeMsrKey) {
        this.treeMsrKey = treeMsrKey;
    }

    public int getTreeKey() {
        return treeKey;
    }

    public void setTreeKey(int treeKey) {
        this.treeKey = treeKey;
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

    public String getQualClass5Code() {
        return qualClass5Code;
    }

    public void setQualClass5Code(String qualClass5Code) {
        this.qualClass5Code = qualClass5Code;
    }

    public String getQualClass6Code() {
        return qualClass6Code;
    }

    public void setQualClass6Code(String qualClass6Code) {
        this.qualClass6Code = qualClass6Code;
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

    public boolean isBackfill() {
        return backfill;
    }

    public void setBackfill(boolean backfill) {
        this.backfill = backfill;
    }

    public TreeMsr(int treeMsrKey, int treeKey, String treeStatusCode, double htToDBH, double dbh, String qualClass2Code, String qualClass5Code, String qualClass6Code, int liveCrownRatio, String crownClassCode, int crownPosnCode, int crownLight, boolean htTree, boolean htTreeCircled, int decayClass, double ocularLength, boolean brokenTop, int crownCondCode, int barkRetentionCode, int woodCondCode, String prescripCode, boolean backfill) {
        this.treeMsrKey = treeMsrKey;
        this.treeKey = treeKey;
        this.treeStatusCode = treeStatusCode;
        this.htToDBH = htToDBH;
        this.dbh = dbh;
        this.qualClass2Code = qualClass2Code;
        this.qualClass5Code = qualClass5Code;
        this.qualClass6Code = qualClass6Code;
        this.liveCrownRatio = liveCrownRatio;
        this.crownClassCode = crownClassCode;
        this.crownPosnCode = crownPosnCode;
        this.crownLight = crownLight;
        this.htTree = htTree;
        this.htTreeCircled = htTreeCircled;
        this.decayClass = decayClass;
        this.ocularLength = ocularLength;
        this.brokenTop = brokenTop;
        this.crownCondCode = crownCondCode;
        this.barkRetentionCode = barkRetentionCode;
        this.woodCondCode = woodCondCode;
        this.prescripCode = prescripCode;
        this.backfill = backfill;
    }
}
