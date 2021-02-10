package gydatainput.models.soilsample;

/**
 * Soil Photo
 *
 */
public class SoilPhoto {
    private int soilPhotoKey;
    private int soilSampleKey;
    private String description;
    private int frameNum;
    private int photoYear;

    /**
     * SoilPhoto Constructor
     * @param soilPhotoKey
     * @param soilSampleKey
     * @param description
     * @param frameNum
     * @param photoYear
     */
    public SoilPhoto(int soilPhotoKey, int soilSampleKey, String description, int frameNum, int photoYear) {
        this.soilPhotoKey = soilPhotoKey;
        this.soilSampleKey = soilSampleKey;
        this.description = description;
        this.frameNum = frameNum;
        this.photoYear = photoYear;
    }

    public int getSoilPhotoKey() {
        return soilPhotoKey;
    }

    public void setSoilPhotoKey(int soilPhotoKey) {
        this.soilPhotoKey = soilPhotoKey;
    }

    public int getSoilSampleKey() {
        return soilSampleKey;
    }

    public void setSoilSampleKey(int soilSampleKey) {
        this.soilSampleKey = soilSampleKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(int frameNum) {
        this.frameNum = frameNum;
    }

    public int getPhotoYear() {
        return photoYear;
    }

    public void setPhotoYear(int photoYear) {
        this.photoYear = photoYear;
    }
}
