package gydatainput.models.plotpackage;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.age.AgeHeader;
import gydatainput.models.downwoodydebris.DWD;
import gydatainput.models.downwoodydebris.DWDHeader;
import gydatainput.models.height.HtHeader;
import gydatainput.models.mortality.MortHeader;
import gydatainput.models.photo.PhotoHeader;
import gydatainput.models.plotmapping.PlotMapHeader;
import gydatainput.models.selfqa.SelfQAHeader;
import gydatainput.models.soilsitetemporal.SoilEcositeHeader;
import gydatainput.models.soilsitetemporal.SoilHeader;
import gydatainput.models.standinformation.StandInfoHeader;
import gydatainput.models.stocking.StkgHeader;
import gydatainput.models.tree.TreeHeader;
import gydatainput.models.vegetation.VegHeader;
import org.json.simple.JSONObject;

import java.sql.SQLException;

public class Visit extends Table {
    private StandInfoHeader standInfoHeader;
    private PhotoHeader photoHeader;
    private VegHeader vegHeader;
    private TreeHeader treeHeader;
    private HtHeader htHeader;
    private DWDHeader dwdHeader;
    private PlotMapHeader plotMapHeader;
    private StkgHeader stkgHeader;
    private MortHeader mortHeader;
    private AgeHeader ageHeader;
    private SoilEcositeHeader soilEcositeHeader;
    private SoilHeader soilHeader;
    private SelfQAHeader selfQAHeader;

    public Visit() {
    }

    public void retrieveData() {
        try {
            this.standInfoHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblStandInfoHeader", StandInfoHeader.class);
            this.photoHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblPhotoHeader", PhotoHeader.class);
            this.vegHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblVegHeader", VegHeader.class);
            this.treeHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblTreeHeader", TreeHeader.class);
            this.htHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblHtHeader", HtHeader.class);
            this.dwdHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblDWDHeader", DWDHeader.class);
            this.plotMapHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblPlotMapHeader", PlotMapHeader.class);
            this.stkgHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblStkgHeader", StkgHeader.class);
            this.mortHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblMortHeader", MortHeader.class);
            this.ageHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblAgeHeader", AgeHeader.class);
            this.soilEcositeHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblSoilEcositeHeader", SoilEcositeHeader.class);
            this.soilHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblSoilHeader", SoilHeader.class);
            this.selfQAHeader = DatabaseHelper.getData(getKey(), getKeyName(), "tblSelfQAHeader", SelfQAHeader.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        json.put("fields", this.getFields());

        if (standInfoHeader != null) {
            json.put("tblStandInfoHeader", standInfoHeader.getJSON());
        }

        if (photoHeader != null) {
            json.put("tblPhotoHeader", photoHeader.getJSON());
        }

        if (vegHeader != null) {
            json.put("tblVegHeader", vegHeader.getJSON());
        }

        if (treeHeader != null) {
            json.put("tblTreeHeader", treeHeader.getJSON());
        }

        if (htHeader != null) {
            json.put("tblHtHeader", htHeader.getJSON());
        }

        if (dwdHeader != null) {
            json.put("tblDWDHeader", dwdHeader.getJSON());
        }

        if (plotMapHeader != null) {
            json.put("tblPlotMapHeader", plotMapHeader.getJSON());
        }

        if (stkgHeader != null) {
            json.put("tblStkgHeader", stkgHeader.getJSON());
        }

        if (mortHeader != null) {
            json.put("tblMortHeader", mortHeader.getJSON());
        }

        if (ageHeader != null) {
            json.put("tblAgeHeader", ageHeader.getJSON());
        }

        if (soilEcositeHeader != null) {
            json.put("tblSoilEcositeHeader", soilEcositeHeader.getJSON());
        }

        if (soilHeader != null) {
            json.put("tblSoilHeader", soilHeader.getJSON());
        }

        if (selfQAHeader != null) {
            json.put("tblSelfQAHeader", selfQAHeader.getJSON());
        }

        return json;
    }
}
