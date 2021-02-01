package gydatainput.models.photo;

public class PhotoRequired {
    private int photoRequiredKey;
    private int photoTypeCode;
    private int frameNum;

    public int getPhotoRequiredKey() {
        return photoRequiredKey;
    }

    public void setPhotoRequiredKey(int photoRequiredKey) {
        this.photoRequiredKey = photoRequiredKey;
    }

    public int getPhotoTypeCode() {
        return photoTypeCode;
    }

    public void setPhotoTypeCode(int photoTypeCode) {
        this.photoTypeCode = photoTypeCode;
    }

    public int getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(int frameNum) {
        this.frameNum = frameNum;
    }

    public PhotoRequired(int photoRequiredKey, int photoTypeCode, int frameNum) {
        this.photoRequiredKey = photoRequiredKey;
        this.photoTypeCode = photoTypeCode;
        this.frameNum = frameNum;
    }
}
