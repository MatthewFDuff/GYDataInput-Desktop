package gydatainput.models.vegetation;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class VegPlot extends Table {

    private ArrayList<VegCover> vegCover;
    private ArrayList<VegRegen> vegRegen;
    private ArrayList<VegSpecPres> vegSpecPres;
    private ArrayList<VegShrubSpec> vegShrubSpec;

    public VegPlot() {
    }

    @Override
    public void fetchData() throws SQLException {
        this.vegCover = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblVegCover", VegCover.class);
        this.vegRegen = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblVegRegen", VegRegen.class);
        this.vegSpecPres = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblVegSpecPres", VegSpecPres.class);
        this.vegShrubSpec = DatabaseHelper.getObjects(getKey(), getKeyName(), "tblVegShrubSpec", VegShrubSpec.class);
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        // Veg Cover
        JSONArray vegCoverJSON = getChildFieldArray(vegCover);
        if(vegCoverJSON != null) {
            json.put("tblVegCover", vegCoverJSON);
        }

        // Veg Regen
        JSONArray vegRegenJSON = getChildFieldArray(vegRegen);
        if(vegRegenJSON != null) {
            json.put("tblVegRegen", vegRegenJSON);
        }

        // Veg Spec Pres
        JSONArray vegSpecPresJSON = getChildFieldArray(vegSpecPres);
        if(vegSpecPresJSON != null) {
            json.put("tblVegSpecPres", vegSpecPresJSON);
        }

        // Veg Shrub Spec
        JSONArray vegShrubSpecJSON = getChildFieldArray(vegShrubSpec);
        if(vegShrubSpecJSON != null) {
            json.put("tblVegShrubSpec", vegShrubSpecJSON);
        }

        return json;
    }
}
