package gydatainput.models.soilsitetemporal;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.soilsitemacromesomicro.SoilGrowthPlot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class SoilHeader extends Table {

    private ArrayList<SoilForestFloor> soilForestFloor;
    private ArrayList<SoilGroundCover> soilGroundCover;

    public SoilHeader() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.soilForestFloor = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilForestFloor", SoilForestFloor.class);
        this.soilGroundCover = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilGroundCover", SoilGroundCover.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        if (!soilForestFloor.isEmpty()) {
            JSONArray soilForestFloorJSON = new JSONArray();
            soilForestFloor.forEach((n) -> {
                soilForestFloorJSON.add(n.getFields());
            });
            json.put("SoilForestFloor", soilForestFloorJSON);
        }

        if (!soilGroundCover.isEmpty()) {
            JSONArray soilGroundCoverJSON = new JSONArray();
            soilGroundCover.forEach((n) -> {
                soilGroundCoverJSON.add(n.getFields());
            });
            json.put("SoilForestFloor", soilGroundCoverJSON);
        }

        return json;
    }
}
