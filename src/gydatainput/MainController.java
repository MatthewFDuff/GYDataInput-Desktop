package gydatainput;

import gydatainput.database.DatabaseController;
import gydatainput.database.DatabaseHelper;
import gydatainput.models.age.AgeHeader;
import gydatainput.models.cavity.TreeCav;
import gydatainput.models.deformity.TreeDefm;
import gydatainput.models.downwoodydebris.DWDHeader;
import gydatainput.models.downwoodydebris.DWDLine;
import gydatainput.models.height.HtHeader;
import gydatainput.models.location.LocCoord;
import gydatainput.models.location.LocPlot;
import gydatainput.models.location.PlotAccess;
import gydatainput.models.mortality.MortHeader;
import gydatainput.models.note.Note;
import gydatainput.models.note.NoteFixup;
import gydatainput.models.note.NotePlot;
import gydatainput.models.photo.PhotoFeature;
import gydatainput.models.photo.PhotoHeader;
import gydatainput.models.photo.PhotoRequired;
import gydatainput.models.plotmapping.PlotMapGrowthPlot;
import gydatainput.models.plotmapping.PlotMapHeader;
import gydatainput.models.plotmapping.PlotMapMort;
import gydatainput.models.plotpackage.Package;
import gydatainput.models.plotpackage.Visit;
import gydatainput.models.selfqa.SelfQAHeader;
import gydatainput.models.sitepermissions.SitePermPlot;
import gydatainput.models.sitepermissions.SitePermRest;
import gydatainput.models.soilsitetemporal.SoilEcositeHeader;
import gydatainput.models.soilsitetemporal.SoilHeader;
import gydatainput.models.standinformation.*;
import gydatainput.models.stocking.StkgHeader;
import gydatainput.models.tree.*;
import gydatainput.models.vegetation.*;
import gydatainput.ui.exportplotpackage.ExportPlotPackageController;
import gydatainput.ui.plotpackage.PlotPackageController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.xml.crypto.Data;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 *  The main controller class controls the main UI functionality and
 *  initializes all other controllers, including the database.
 *
 * @author Matthew Duff
 * @version 0.1
 * @since 2020-11-28
 * */
public class MainController implements Initializable {
    @FXML ListView<Package> listCompleted;
    @FXML ListView<Package> listExports;
    @FXML Button btnImportPackage;
    @FXML Button btnDownloadPackage;
    @FXML TextField txtFilterCompleted;
    @FXML Button btnOpenFileExportsFolder;

    // A list of all plot packages that are currently in the database.
    public ObservableList<Package> packages = FXCollections.observableArrayList();
    // A list of all plot packages that are currently in the export list.
    public ObservableList<Package> exportPackages = FXCollections.observableArrayList();

    // DO NOT REMOVE (This instantiates the database controller on startup and is required, despite not being referenced in this file)
    DatabaseController database = DatabaseController.getInstance();

    Path exportPath = Paths.get(System.getProperty("user.dir") + "/exports/");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPackages(); // Gets the packages from the database and populates the completed packages list.

        // The FilteredList is built from the ObservableList so we can update it each time the filter changes.
        FilteredList<Package> filteredPackages = new FilteredList<>(packages);

        // Update the list each time the filter TextField is updated by the user.
        // Reference: https://code.makery.ch/blog/javafx-8-tableview-sorting-filtering/
        txtFilterCompleted.textProperty().addListener((observable, oldValue, newValue) -> {
            // The predicate is what allows us to filter the list dynamically.
            filteredPackages.setPredicate(plotPackage -> {
                // If the filter field is empty, show all packages.
                if(newValue == null || newValue.isEmpty()) {
                    return true; // All items will appear in the list.
                }

                // Compare the filter with the name of the plot package (both lowercase).
                String lowerCaseFilter = newValue.toLowerCase();
                if(plotPackage.getPlot().getFields().get("PlotKey").toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // This item will appear in the list.
                }

                return false; // This item will not appear in the list.
            });
        });

        // Update the list of completed packages.
        listCompleted.setItems(filteredPackages);

        // The cell factory provides each plot package a list item UI element.
        listCompleted.setCellFactory(completedListView -> new PlotPackageController());
        listExports.setCellFactory(exportListView -> new ExportPlotPackageController());

        // This allows us to get the currently selected items from the completed package list.
        listCompleted.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Package>() {
            @Override
            public void changed(ObservableValue<? extends Package> observableValue, Package oldPackage, Package newPackage) {
                System.out.println("Completed selection changed from oldValue = "
                        + oldPackage + " to newValue = " + newPackage);
            }
        });

        // This allows us to get the currently selected items from the export package list.
        listExports.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Package>() {
            @Override
            public void changed(ObservableValue<? extends Package> observableValue, Package oldPackage, Package newPackage) {
                System.out.println("Export selection changed from oldValue = "
                        + oldPackage + " to newValue = " + newPackage);
            }
        });

        System.out.println(System.getProperty("user.dir"));
    }

    /**
     * Opens the file with the System default file explorer.
     * @param
     */
    @FXML
    public void openExportFileLocation(ActionEvent actionEvent) throws IOException {
        final String EXPLORER_EXE = "explorer.exe";
        String command = "explorer.exe /select," + exportPath;
        Runtime.getRuntime().exec(command);

    }

    /** Load Packages
     *      This function requests all plot packages from the database
     *      and adds them to the list of "Completed" packages.
     * @return void
     * @exception
     * */
    public void loadPackages() {
        if (DatabaseHelper.loadPlotPackages(packages)) {
            for(Package pPackage : packages) {
                listCompleted.getItems().add(pPackage);
            }

            System.out.println("Plot packages loaded.");
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to load plot packages.");
            alert.showAndWait();
        }
    }

    @FXML
    /** Export Plot Packages
     *      This function will export all plot packages from the exports list and
     *      create individual JSON files for each of them in a contained folder.
     * @param
     */
    void exportPlotPackages(ActionEvent event) {
        try {
            // Create a new timestamped folder for the exported packages.
            Path path = Paths.get(System.getProperty("user.dir") + "/exports/" + LocalDate.now() + "-" + System.currentTimeMillis());
            exportPath = path;
            Files.createDirectory(path);

            // Iterate through each package in the exports list.
            for (Package pkg: listExports.getItems()) {
                System.out.println(pkg.getPlot().getFields().get("PlotName"));
                // Retrieve all data for the plot package from the database
                pkg.loadPackage();

                JSONObject pkgJSON = pkg.getJSON();

                // Create JSON file
                FileWriter file = new FileWriter(path + "/" + pkg.getPlot().getFields().get("PlotName") + ".json");
                file.write(pkgJSON.toJSONString());
                file.close();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to create new exports directory.");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("An unexpected error has occurred. Unable to export plot packages.\n\n"+e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    /** Download Plot Package
     *      This function downloads the provided plot package as a JSON
     *      object, which can then be sent to the field crew to be used
     *      in validation and flagging.
     * @param pkg The plot package which will be downloaded.
     * */
    void downloadPlotPackage(Package pkg) {
        try {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Updating info for package " + pkg.getPlot().getFields().get("PlotName"));
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to display package data.");
            alert.showAndWait();
        }
    }

    @FXML
    /** Add Plot Package To Export List
     *      This function adds the selected plot package in the completed list to the
     *      export list.
     * @param event The onClick event when the button is clicked.
     * */
    void addPackageToExportList(ActionEvent event) {
        // Note that the user can only select one plot package at a time
        // so multiple selections is not a case that needs to be covered.
        try {
            // Get the selected plot package from the Completed list.
            Package selected = listCompleted.getSelectionModel().getSelectedItem();

            // Check that the selection is not null.
            if (selected != null) {
                // Check if the plot package is already in the Exports list.
                if (!listExports.getItems().contains(selected))
                    // If it's not in the list, add it.
                    listExports.getItems().add(selected);
                else {
                    // Otherwise notify the user that it is already in the Exports list.
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("This plot package is already in the exports list.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to add plot package to export list.");
            alert.showAndWait();
        }
    }

    @FXML
    /** Remove Plot Package From Export List
     *      This function removes the selected plot package from the exports list.
     * @param event The onClick event when the button is clicked.
     * */
    void removePackageFromExportList(ActionEvent event) {
        // Note that the user can only select one plot package at a time
        // so multiple selections is not a case that needs to be covered.
        try {
            // Get the selected plot package from the Exports list.
            Package selected = listExports.getSelectionModel().getSelectedItem();
            // Remove it from the list.
            listExports.getItems().remove(selected);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to remove plot package from the export list.");
            alert.showAndWait();
        }
    }
}
