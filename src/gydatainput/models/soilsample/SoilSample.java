package gydatainput.models.soilsample;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
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

    @Override
    public void fetchData() throws SQLException {
        this.soilDepMode = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilDepMode", SoilDepMode.class);
        this.soilPhoto = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilPhoto", SoilPhoto.class);
        this.soilHor = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilHor", SoilHor.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        if (soilDepMode != null && !soilDepMode.isEmpty()) {
            JSONArray soilDepModeJSON = new JSONArray();
            soilDepMode.forEach((n) -> {
                soilDepModeJSON.add(n.getFields());
            });
            json.put("SoilDepMode", soilDepModeJSON);
        }

        if (soilPhoto != null && !soilPhoto.isEmpty()) {
            JSONArray soilPhotoJSON = new JSONArray();
            soilPhoto.forEach((n) -> {
                soilPhotoJSON.add(n.getFields());
            });
            json.put("SoilPhoto", soilPhotoJSON);
        }

        if (soilHor != null && !soilHor.isEmpty()) {
            JSONArray soilHorJSON = new JSONArray();
            soilHor.forEach((n) -> {
                soilHorJSON.add(n.getFields());
            });
            json.put("SoilHor", soilHorJSON);
        }

        return json;
    }
}
