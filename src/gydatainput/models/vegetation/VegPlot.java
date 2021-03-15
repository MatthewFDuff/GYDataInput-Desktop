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
        if (!vegCover.isEmpty()) {
            JSONArray vegCoverJSON = new JSONArray();
            vegCover.forEach((n) -> {
                vegCoverJSON.add(n.getFields());
            });
            json.put("VegCover", vegCoverJSON);
        }

        // Veg Regen
        if (!vegRegen.isEmpty()) {
            JSONArray vegRegenJSON = new JSONArray();
            vegRegen.forEach((n) -> {
                vegRegenJSON.add(n.getFields());
            });
            json.put("VegRegen", vegRegenJSON);
        }

        // Veg Spec Pres
        if (!vegSpecPres.isEmpty()) {
            JSONArray vegSpecPresJSON = new JSONArray();
            vegSpecPres.forEach((n) -> {
                vegSpecPresJSON.add(n.getFields());
            });
            json.put("VegSpecPres", vegSpecPresJSON);
        }

        // Veg Shrub Spec
        if (!vegShrubSpec.isEmpty()) {
            JSONArray vegShrubSpecJSON = new JSONArray();
            vegShrubSpec.forEach((n) -> {
                vegShrubSpecJSON.add(n.getFields());
            });
            json.put("VegShrubSpec", vegShrubSpecJSON);
        }

        return json;
    }
}
