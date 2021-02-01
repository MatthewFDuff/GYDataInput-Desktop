package gydatainput.models.photo;

public class PhotoHeader {
    private int photoHeaderKey;
    private String msrDate;

    private PhotoRequired[] photoRequireds;
    private PhotoFeature[] photoFeatures;

    public PhotoRequired[] getPhotoRequireds() {
        return photoRequireds;
    }

    public void setPhotoRequireds(PhotoRequired[] photoRequireds) {
        this.photoRequireds = photoRequireds;
    }

    public PhotoFeature[] getPhotoFeatures() {
        return photoFeatures;
    }

    public void setPhotoFeatures(PhotoFeature[] photoFeatures) {
        this.photoFeatures = photoFeatures;
    }

    public int getPhotoHeaderKey() {
        return photoHeaderKey;
    }

    public void setPhotoHeaderKey(int photoHeaderKey) {
        this.photoHeaderKey = photoHeaderKey;
    }

    public String getMsrDate() {
        return msrDate;
    }

    public void setMsrDate(String msrDate) {
        this.msrDate = msrDate;
    }

    public PhotoHeader(int photoHeaderKey, String msrDate) {
        this.photoHeaderKey = photoHeaderKey;
        this.msrDate = msrDate;
    }
}
