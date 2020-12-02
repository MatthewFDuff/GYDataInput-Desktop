package gydatainput.database;

import gydatainput.models.plotpackage.PlotPackage;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The Database Helper class provides the application with all database query functions.
 * The functions are static and can be referenced from other classes, making it easy to
 * perform queries and pass database results to any class that needs it.
 *
 * @author Matthew Duff
 * @version 0.1
 * @since 2020-11-28
 */
public class DatabaseHelper {

    /*

// Get package, visit, and plot information
SELECT *
  FROM [gyPSPPGP].[dbo].[tblPackage] AS package
  LEFT JOIN [gyPSPPGP].[dbo].[tblVisit] AS visit
  ON package.PackageKey = visit.PackageKey
  LEFT JOIN [gyPSPPGP].[dbo].[tblPlot] AS plot
  ON package.PlotKey = plot.PlotKey
  WHERE visit.VisitKey IS NOT NULL
  // *** Make sure that the plot has actually been visited before.

    // Get the TREE form information (tree records)
    SELECT *
FROM [gyPSPPGP].[dbo].[tblTree] AS tree
LEFT JOIN [gyPSPPGP].[dbo].[tblTreeMsr] AS msr
ON tree.TreeKey = msr.TreeKey

    */

    /** Load Plot Packages
     *
     *
     * */
    public static boolean loadPlotPackages(ObservableList<PlotPackage> packageList) {
        try {
            String query = "SELECT *" +
                    " FROM dbo.tblPackage AS package" +
                    " LEFT JOIN dbo.tblVisit AS visit" +
                    " ON package.PackageKey = visit.PackageKey" +
                    " LEFT JOIN dbo.tblPlot AS plot" +
                    " ON package.PlotKey = plot.PlotKey" +
                    " WHERE visit.VisitKey IS NOT NULL";

            ResultSet result = DatabaseController.executeQuery(query);

            // Iterate through results.
            while(result.next()) {
                String plotName = result.getString("PlotName");
                String fieldSeasonYear = result.getString("FieldSeasonYear");
                int manualCode = result.getInt("ManualCode");
                int approachCode = result.getInt("ApproachCode");
                int visitTypeCode = result.getInt("VisitTypeCode");

                // Add the plot package to the list.
                 packageList.add(new PlotPackage(plotName,fieldSeasonYear, manualCode, approachCode, visitTypeCode, true));
            }
            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to get plot packages from database.");
            alert.showAndWait();
            return false;
        }
    }
}
