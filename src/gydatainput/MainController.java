package gydatainput;

import gydatainput.database.DatabaseController;
import gydatainput.database.DatabaseHelper;
import gydatainput.models.plotpackage.PlotPackage;
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

import java.net.URL;
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
    @FXML ListView<PlotPackage> listCompleted;
    @FXML ListView<PlotPackage> listExports;
    @FXML Button btnImportPackage;
    @FXML Button btnDownloadPackage;
    @FXML TextField txtFilterCompleted;

    // A list of all plot packages that are currently in the database.
    public ObservableList<PlotPackage> packages = FXCollections.observableArrayList();
    public ObservableList<PlotPackage> exportPackages = FXCollections.observableArrayList();

    // DO NOT REMOVE (This instantiates the database controller on startup and is required, despite not being referenced in this file)
    DatabaseController database = DatabaseController.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPackages(); // Gets the packages from the database and populates the completed packages list.

        // The FilteredList is built from the ObservableList so we can update it each time the filter changes.
        FilteredList<PlotPackage> filteredPackages = new FilteredList<>(packages);

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
                if(plotPackage.getName().toLowerCase().contains(lowerCaseFilter)) {
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
        listCompleted.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlotPackage>() {
            @Override
            public void changed(ObservableValue<? extends PlotPackage> observableValue, PlotPackage oldPackage, PlotPackage newPackage) {
                System.out.println("Completed selection changed from oldValue = "
                        + oldPackage + " to newValue = " + newPackage);
            }
        });

        // This allows us to get the currently selected items from the export package list.
        listExports.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<PlotPackage>() {
            @Override
            public void changed(ObservableValue<? extends PlotPackage> observableValue, PlotPackage oldPackage, PlotPackage newPackage) {
                System.out.println("Export selection changed from oldValue = "
                        + oldPackage + " to newValue = " + newPackage);
            }
        });
    }

    /** Load Packages
     *      This function requests all plot packages from the database
     *      and adds them to the list of "Completed" packages.
     * @return void
     * @exception
     * */
    public void loadPackages() {
        if (DatabaseHelper.loadPlotPackages(packages)) {
            for(PlotPackage pPackage : packages) {
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
    /** Download Plot Package
     *      This function downloads the selected plot package as a JSON
     *      object, which can then be sent to the field crew to be used
     *      in validation and flagging.
     * @param event The onClick event when the Download Plot Package button is clicked.
     * */
    void downloadPlotPackage(ActionEvent event) {
        try {
            //PlotPackage selected = PlotPackageController.getSelected().getPlotPackage();
            PlotPackage selected = listCompleted.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("{" +
                    "\n\t'plot-name': " + selected.getName() +
                    "\n\t'field-season-year': " + selected.getDate() +
                    "\n\t'manual': " + selected.getManual() +
                    "\n\t'approach': " + selected.getApproach() +
                    "\n\t'visit-type': " + selected.getVisitType() +
                    "\n\t'completed': " + selected.isCompleted() +
                    "\n}");
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
        try {
            PlotPackage selected = listCompleted.getSelectionModel().getSelectedItem();
            listExports.getItems().add(selected);
            // TODO: Prevent adding a plot package to the list more than once.
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Unable to add plot package to export list.");
            alert.showAndWait();
        }
    }
}
