package gydatainput.database;

import gydatainput.models.plotpackage.PlotPackage;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseHelper {

    // Get all plot package names from the database.
    public static boolean loadPlotPackages(ObservableList<PlotPackage> packageList) {
        try {
            String query = "SELECT TOP (20) * FROM dbo.tblPlot";
            ResultSet result = DatabaseController.executeQuery(query);

            while(result.next()) {
                //System.out.println(result.getString("PlotName"));
                String name = result.getString("PlotName");
                LocalDate date = LocalDate.now();

                packageList.add(new PlotPackage(date, name, false));
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
