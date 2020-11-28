package gydatainput;

import gydatainput.database.DatabaseController;
import gydatainput.database.DatabaseHelper;
import gydatainput.models.plotpackage.PlotPackage;
import gydatainput.ui.plotpackage.PlotPackageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML ListView<PlotPackage> listCompleted;
    @FXML Button btnImportPackage;

    public ObservableList<PlotPackage> packages = FXCollections.observableArrayList();

    DatabaseController database = DatabaseController.getInstance(); // DO NOT REMOVE (This instantiates the database controller on startup and is required, despite not being referenced directly in this file)

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPackages();

        listCompleted.setCellFactory(completedListView -> new PlotPackageController());
    }

    public void loadPackages() {
        listCompleted.getItems().clear();

        try {
            if (DatabaseHelper.loadPlotPackages(packages)) {
                for(PlotPackage pPackage : packages) {
                    listCompleted.getItems().add(pPackage);
                }

                System.out.println("Plot packages loaded.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to load plot packages.");
            alert.showAndWait();
        }
    }
}
