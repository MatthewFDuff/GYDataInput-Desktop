package gydatainput.models.tree;

public class TreeMissed {
    private int treeMissedKey;
    private boolean missed;

    public int getTreeMissedKey() {
        return treeMissedKey;
    }

    public void setTreeMissedKey(int treeMissedKey) {
        this.treeMissedKey = treeMissedKey;
    }

    public boolean isMissed() {
        return missed;
    }

    public void setMissed(boolean missed) {
        this.missed = missed;
    }

    public TreeMissed(int treeMissedKey, boolean missed) {
        this.treeMissedKey = treeMissedKey;
        this.missed = missed;
    }
}
