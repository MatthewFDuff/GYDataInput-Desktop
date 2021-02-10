package gydatainput.models.plotpackage;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.specialist.SpecGYHeader;
import org.json.simple.JSONObject;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

public class Package extends Table {
    private Plot plot;
    private ArrayList<Visit> visits;

    private SpecGYHeader specGYHeader;

    public Package(JSONObject fields) {
        super(fields);

        int plotKey = (int) this.getFields().get("PlotKey");
        int packageKey = (int) this.getFields().get("PackageKey");

        this.plot = new Plot(DatabaseHelper.getData(plotKey, "PlotKey", "tblPlot"));
        this.visits = DatabaseHelper.getObjects(packageKey, "PackageKey", "tblVisit");
    }

}
