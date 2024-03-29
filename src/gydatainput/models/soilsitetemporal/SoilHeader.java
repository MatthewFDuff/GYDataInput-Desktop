package gydatainput.models.soilsitetemporal;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotpackage.Visit;
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

    public SoilHeader(JSONObject json, boolean isImport) {
        super(json, isImport);

        // SoilForestFloor
        this.soilForestFloor = getArrayFromJSON(json, "tblSoilForestFloor", SoilForestFloor.class);

        // SoilGroundCover
        this.soilGroundCover = getArrayFromJSON(json, "tblSoilGroundCover", SoilGroundCover.class);
    }

    @Override
    public void upload() throws SQLException {
        super.upload();

        uploadArray(soilForestFloor);
        uploadArray(soilGroundCover);
    }

    @Override
    public void fetchData() throws SQLException {
        this.soilForestFloor = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilForestFloor", SoilForestFloor.class);
        this.soilGroundCover = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblSoilGroundCover", SoilGroundCover.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // SoilForestFloor
        JSONArray soilForestFloorJSON = getChildFieldArray(soilForestFloor);
        if(soilForestFloorJSON != null) {
            json.put("tblSoilForestFloor", soilForestFloorJSON);
        }

        // SoilGroundCover
        JSONArray soilGroundCoverJSON = getChildFieldArray(soilGroundCover);
        if(soilGroundCoverJSON != null) {
            json.put("tblSoilForestFloor", soilGroundCoverJSON);
        }

        return json;
    }
}
