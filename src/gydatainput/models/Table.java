package gydatainput.models;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.age.AgeHeader;
import gydatainput.models.age.AgeSample;
import gydatainput.models.age.AgeTree;
import gydatainput.models.cavity.TreeCav;
import gydatainput.models.deformity.TreeDefm;
import gydatainput.models.downwoodydebris.*;
import gydatainput.models.height.Ht;
import gydatainput.models.height.HtHeader;
import gydatainput.models.location.LocPlot;
import gydatainput.models.mortality.MortHeader;
import gydatainput.models.mortality.MortTreeMsr;
import gydatainput.models.note.Note;
import gydatainput.models.note.NoteFixup;
import gydatainput.models.note.NotePlot;
import gydatainput.models.photo.PhotoFeature;
import gydatainput.models.photo.PhotoHeader;
import gydatainput.models.photo.PhotoRequired;
import gydatainput.models.plotmapping.PlotMapGrowthPlot;
import gydatainput.models.plotmapping.PlotMapHeader;
import gydatainput.models.plotmapping.PlotMapMort;
import gydatainput.models.plotpackage.Plot;
import gydatainput.models.plotpackage.Visit;
import gydatainput.models.selfqa.SelfQADeform;
import gydatainput.models.selfqa.SelfQAHt;
import gydatainput.models.selfqa.SelfQATree;
import gydatainput.models.sitepermissions.SitePermPlot;
import gydatainput.models.sitepermissions.SitePermRest;
import gydatainput.models.soilhorizon.SoilHor;
import gydatainput.models.soilsample.SoilDepMode;
import gydatainput.models.soilsample.SoilPhoto;
import gydatainput.models.soilsample.SoilSample;
import gydatainput.models.soilsitemacromesomicro.SoilGrowthPlot;
import gydatainput.models.soilsitemacromesomicro.SoilPlot;
import gydatainput.models.soilsitetemporal.*;
import gydatainput.models.specialist.SpecAssoc;
import gydatainput.models.specialist.SpecGYHeader;
import gydatainput.models.standinformation.*;
import gydatainput.models.stocking.Stkg;
import gydatainput.models.stocking.StkgHeader;
import gydatainput.models.tree.*;
import gydatainput.models.vegetation.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Table represents a table from the Growth & Yield database.
 */
public class Table {
    private JSONArray fields;
    private int key;
    private String keyName;

    public Table() {}

    /**
     * Construct a table with a JSONObject containing all columns of a database table.
     * @param fields each value contained in a column
     */
    public Table(JSONArray fields) {
        this.fields = fields;

        // Create an iterator to go through each field.
        Iterator<JSONObject> iterator = fields.iterator();
        if(iterator.hasNext()) {
            JSONObject field = iterator.next(); // Get the next object.

            // Key Set
            Iterator<String> keys = (Iterator<String>) field.keySet().iterator();
            String primaryKey = keys.next();
            // The first key in the set is the primary key for the table.
            this.setKeyName(primaryKey);
            // Use the first key in the set to get the value of the key.
            this.key = (int) field.get(this.keyName);
        }
    }

    // Constructor that is used when importing a plot package. TODO Merge this constructor with the other one.
    public Table(JSONObject json, boolean isImport) {
        JSONArray fields = (JSONArray) json.get("fields");
        this.setFields(fields);
    }

    // Supply the name of a field and this will grab it from the JSONArray of fields if it exists.
    public Object getFromFields(String field) {
        try {
            Iterator<JSONObject> iterator = this.fields.iterator();
            while (iterator.hasNext()) {
                JSONObject fieldJSON = iterator.next();
                // Check if the current field matches the one being requested.
                if (fieldJSON.get(field) != null) {
                    return fieldJSON.get(field);
                }
            }

            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void fetchData() throws SQLException {
        return;
    }

    protected JSONArray getFields() {
        return fields;
    }



    public static <T extends Table> JSONArray getChildFieldArray(ArrayList<T> children) {
        if (!children.isEmpty()) {
            JSONArray childrenJSON = new JSONArray();
            children.forEach((n) -> {
                JSONObject child = new JSONObject();
                child.put("fields", n.getFields());
                childrenJSON.add(child);
            });

            return childrenJSON;
        }
        return null;
    }

    public static <T extends Table> JSONArray getChildFieldArrayWithJSON(ArrayList<T> children) {
        if (!children.isEmpty()) {
            JSONArray childrenJSON = new JSONArray();
            children.forEach((n) -> {
                childrenJSON.add(n.getJSON());
            });

            return childrenJSON;
        }
        return null;
    }

    // Used to get an array of objects from imported JSON files.
    public static <T extends Table> ArrayList<T> getArrayFromJSON(JSONObject json, String table, Class<T> classType) {
        ArrayList<T> list = new ArrayList<>(); // Create an array list.
        JSONArray listJSON = (JSONArray) json.get(table); // Get the array of tables from the parent JSON object.

        if (listJSON != null) {
            // Iterate through the json objects within the array.
            for(JSONObject jsonObject : (Iterable<JSONObject>) listJSON) {
                // Create a new instance of the object based on the class type provided.
                switch(classType.toString()) {
                    case "class gydatainput.models.plotpackage.Visit":
                        list.add((T) new Visit(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.Plot":
                        list.add((T) new Plot(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.AgeTree":
                        list.add((T) new AgeTree(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.AgeSample":
                        list.add((T) new AgeSample(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.DWDIntersect":
                        list.add((T) new DWDIntersect(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.DWDLine":
                        list.add((T) new DWDLine(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.DWD":
                        list.add((T) new DWD(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.DWDStump":
                        list.add((T) new DWDStump(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.DWDAccum":
                        list.add((T) new DWDAccum(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.Ht":
                        list.add((T) new Ht(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.MortTreeMsr":
                        list.add((T) new MortTreeMsr(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.PhotoRequired":
                        list.add((T) new PhotoRequired(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.PhotoFeature":
                        list.add((T) new PhotoFeature(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.Tree":
                        list.add((T) new Tree(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.Note":
                        list.add((T) new Note(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.NoteFixup":
                        list.add((T) new NoteFixup(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.StandInfoDistb":
                        list.add((T) new StandInfoDistb(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.StandInfoTreat":
                        list.add((T) new StandInfoTreat(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.StandInfoCompr":
                        list.add((T) new StandInfoCompr(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.PlotMapGrowthPlot":
                        list.add((T) new PlotMapGrowthPlot(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SoilSample":
                        list.add((T) new SoilSample(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SoilGrowthPlot":
                        list.add((T) new SoilGrowthPlot(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SpecAssoc":
                        list.add((T) new SpecAssoc(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SelfQAHt":
                        list.add((T) new SelfQAHt(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SelfQATree":
                        list.add((T) new SelfQATree(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SelfQADeform":
                        list.add((T) new SelfQADeform(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SoilDepMode":
                        list.add((T) new SoilDepMode(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SoilPhoto":
                        list.add((T) new SoilPhoto(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SoilHor":
                        list.add((T) new SoilHor(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SoilEcosite":
                        list.add((T) new SoilEcosite(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SoilForestFloor":
                        list.add((T) new SoilForestFloor(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.SoilGroundCover":
                        list.add((T) new SoilGroundCover(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.StkgLine":
                        list.add((T) new Stkg(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.TreeMsr":
                        list.add((T) new TreeMsr(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.TreeGrowthPlot":
                        list.add((T) new TreeGrowthPlot(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.TreeDefm":
                        list.add((T) new TreeDefm(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.TreeCav":
                        list.add((T) new TreeCav(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.VegVType":
                        list.add((T) new VegVType(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.VegPlot":
                        list.add((T) new VegPlot(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.VegCover":
                        list.add((T) new VegCover(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.VegRegen":
                        list.add((T) new VegRegen(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.VegSpecPres":
                        list.add((T) new VegSpecPres(jsonObject, true));
                        break;
                    case "class gydatainput.models.plotpackage.VegShrubSpec":
                        list.add((T) new VegShrubSpec(jsonObject, true));
                        break;
                }
            }
        }

        return list;
    }

    public static <T extends Table> T getObjectFromJSON(JSONObject json, String tableName, Class classType) {
        JSONObject objectJSON = (JSONObject) json.get(tableName);
        if (objectJSON != null) {
            switch (classType.toString()) {
                case "class gydatainput.models.plotpackage.Plot":
                    return (T) new Plot(objectJSON, true);
                case "class gydatainput.models.plotpackage.SpecGYHeader":
                    return (T) new SpecGYHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.NotePlot":
                    return (T) new NotePlot(objectJSON, true);
                case "class gydatainput.models.plotpackage.SitePermRest":
                    return (T) new SitePermRest(objectJSON, true);
                case "class gydatainput.models.plotpackage.SitePermPlot":
                    return (T) new SitePermPlot(objectJSON, true);
                case "class gydatainput.models.plotpackage.LocPlot":
                    return (T) new LocPlot(objectJSON, true);
                case "class gydatainput.models.plotpackage.StandInfoPlot":
                    return (T) new StandInfoPlot(objectJSON, true);
                case "class gydatainput.models.plotpackage.PlotMapMort":
                    return (T) new PlotMapMort(objectJSON, true);
                case "class gydatainput.models.plotpackage.SoilPlot":
                    return (T) new SoilPlot(objectJSON, true);
                case "class gydatainput.models.plotpackage.StandInfoHeader":
                    return (T) new StandInfoHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.PhotoHeader":
                    return (T) new PhotoHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.VegHeader":
                    return (T) new VegHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.TreeHeader":
                    return (T) new TreeHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.HtHeader":
                    return (T) new HtHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.DWDHeader":
                    return (T) new DWDHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.PlotMapHeader":
                    return (T) new PlotMapHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.StkgHeader":
                    return (T) new StkgHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.MortHeader":
                    return (T) new MortHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.AgeHeader":
                    return (T) new AgeHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.SoilEcositeHeader":
                    return (T) new SoilEcositeHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.SoilHeader":
                    return (T) new SoilHeader(objectJSON, true);
                case "class gydatainput.models.plotpackage.TreeMissed":
                    return (T) new TreeMissed(objectJSON, true);
                case "class gydatainput.models.plotpackage.Ht":
                    return (T) new Ht(objectJSON, true);
            }
        }

        return null;
    }

    public Object getJSON() {
        JSONObject fieldJSON = new JSONObject();
        fieldJSON.put("fields", this.getFields());
        return fieldJSON;
    }

    public void setFields(JSONArray fields) { this.fields = fields; }

    public int getKey() {
        return key;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}
