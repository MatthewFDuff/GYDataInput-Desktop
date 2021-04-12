package gydatainput.models.photo;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoHeader extends Table {

    private ArrayList<PhotoRequired> photoRequired;
    private ArrayList<PhotoFeature> photoFeature;

    public PhotoHeader() {
    }

    public PhotoHeader(JSONObject json, boolean isImport) {
        super(json, isImport);

        // PhotoRequired
        this.photoRequired = getArrayFromJSON(json, "tblPhotoRequired", PhotoRequired.class);

        // PhotoFeature
        this.photoFeature = getArrayFromJSON(json, "tblPhotoFeature", PhotoFeature.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(photoRequired);
        uploadArray(photoFeature);
    }

    @Override
    public void fetchData() throws SQLException {
        this.photoRequired = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblPhotoRequired", PhotoRequired.class);
        this.photoFeature = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblPhotoFeature", PhotoFeature.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // PhotoRequired
        JSONArray photoRequiredJSON = getChildFieldArray(photoRequired);
        if (photoRequiredJSON != null) {
            json.put("tblPhotoRequired", photoRequiredJSON);
        }

        // Photo Feature
        JSONArray photoFeatureJSON = getChildFieldArray(photoFeature);
        if (photoFeatureJSON != null) {
            json.put("tblPhotoFeature", photoFeatureJSON);
        }

        return json;
    }
}
