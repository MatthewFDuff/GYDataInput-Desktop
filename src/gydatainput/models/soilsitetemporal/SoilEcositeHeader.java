package gydatainput.models.soilsitetemporal;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class SoilEcositeHeader extends Table {

    private ArrayList<SoilEcosite> soilEcosite;

    public SoilEcositeHeader(){
    }

    @Override
    public void fetchData() throws SQLException {
        this.soilEcosite = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilEcosite", SoilEcosite.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Soil Ecosite
        if (!soilEcosite.isEmpty()) {
            JSONArray soilEcositeJSON = new JSONArray();
            soilEcosite.forEach((n) -> {
                soilEcositeJSON.add(n.getFields());
            });
            json.put("SoilEcosite", soilEcositeJSON);
        }

        return json;
    }
}
