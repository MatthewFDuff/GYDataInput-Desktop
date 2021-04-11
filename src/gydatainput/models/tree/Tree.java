package gydatainput.models.tree;

import gydatainput.database.DatabaseHelper;
import gydatainput.models.Table;
import gydatainput.models.plotmapping.PlotMapGrowthPlot;
import org.json.simple.JSONObject;

import java.sql.SQLException;

/**
 * The Tree class contains data for each individual tree.
 *
 * @author Matthew Duff
 * @version 0.1
 * @since 2020-12-01
 * */
public class Tree extends Table {

    public Tree() {
    }

    public Tree(JSONObject json, boolean isImport) {
        super(json, isImport);
    }
}
