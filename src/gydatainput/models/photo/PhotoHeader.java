package gydatainput.models.photo;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class PhotoHeader extends Table {

    private ArrayList<PhotoRequired> photoRequired;
    private ArrayList<PhotoFeature> photoFeature;

    public PhotoHeader() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.photoRequired = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblPhotoRequired", PhotoRequired.class);
        this.photoFeature = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblPhotoFeature", PhotoFeature.class);
    }

    public JSONObject getJSON() {
        JSONObject json = this.getFields();

        // PhotoRequired
        if (!photoRequired.isEmpty()) {
            JSONArray photoRequiredJSON = new JSONArray();
            photoRequired.forEach((n) -> {
                photoRequiredJSON.add(n.getFields());
            });

            json.put("PhotoRequired", photoRequiredJSON);
        }

        // Photo Feature
        if (!photoFeature.isEmpty()) {
            JSONArray photoFeatureJSON = new JSONArray();
            photoFeature.forEach((n) -> {
                photoFeatureJSON.add(n.getFields());
            });
            json.put("PhotoFeature", photoFeatureJSON);
        }

        return json;
    }
}
