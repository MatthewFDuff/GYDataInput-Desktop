package gydatainput.models.soilsample;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
import gydatainput.models.soilhorizon.SoilHor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class SoilSample extends Table {
    private ArrayList<SoilDepMode> soilDepMode;
    private ArrayList<SoilPhoto> soilPhoto;
    private ArrayList<SoilHor> soilHor;

    public SoilSample(){
    }

    public SoilSample(JSONObject json, boolean isImport) {
        super(json, isImport);

        // SoilDepMode
        this.soilDepMode = getArrayFromJSON(json, "tblSoilDepMode", SoilDepMode.class);

        // SoilPhoto
        this.soilPhoto = getArrayFromJSON(json, "tblSoilPhoto", SoilPhoto.class);

        // SoilHor
        this.soilHor = getArrayFromJSON(json, "tblSoilHor", SoilHor.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(soilDepMode);
        uploadArray(soilPhoto);
        uploadArray(soilHor);
    }

    @Override
    public void fetchData() throws SQLException {
        this.soilDepMode = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilDepMode", SoilDepMode.class);
        this.soilPhoto = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilPhoto", SoilPhoto.class);
        this.soilHor = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilHor", SoilHor.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // SoilDepMode
        JSONArray soilDepModeJSON = getChildFieldArray(soilDepMode);
        if(soilDepModeJSON != null) {
            json.put("tblSoilDepMode", soilDepModeJSON);
        }

        // SoilPhoto
        JSONArray soilPhotoJSON = getChildFieldArray(soilPhoto);
        if(soilPhotoJSON != null) {
            json.put("tblSoilPhoto", soilPhotoJSON);
        }

        // SoilHor
        JSONArray soilHorJSON = getChildFieldArray(soilHor);
        if(soilHorJSON != null) {
            json.put("tblSoilHor", soilHorJSON);
        }

        return json;
    }
}
