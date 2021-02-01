package gydatainput.models.photo;

public class PhotoFeature {
    private int photoFeatureKey;
    private String Description;
    private int frameNum;

    public int getPhotoFeatureKey() {
        return photoFeatureKey;
    }

    public void setPhotoFeatureKey(int photoFeatureKey) {
        this.photoFeatureKey = photoFeatureKey;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(int frameNum) {
        this.frameNum = frameNum;
    }

    public PhotoFeature(int photoFeatureKey, String description, int frameNum) {
        this.photoFeatureKey = photoFeatureKey;
        Description = description;
        this.frameNum = frameNum;
    }
}
