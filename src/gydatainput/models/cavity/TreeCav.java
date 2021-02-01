package gydatainput.models.cavity;

public class TreeCav {
    private int treeCavKey;
    private int cavTypeCode;

    public int getTreeCavKey() {
        return treeCavKey;
    }

    public void setTreeCavKey(int treeCavKey) {
        this.treeCavKey = treeCavKey;
    }

    public int getCavTypeCode() {
        return cavTypeCode;
    }

    public void setCavTypeCode(int cavTypeCode) {
        this.cavTypeCode = cavTypeCode;
    }

    public TreeCav(int treeCavKey, int cavTypeCode) {
        this.treeCavKey = treeCavKey;
        this.cavTypeCode = cavTypeCode;
    }
}
