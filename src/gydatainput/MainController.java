package gydatainput;

import gydatainput.database.DatabaseController;
import gydatainput.database.DatabaseHelper;
import gydatainput.models.plotpackage.Package;
import gydatainput.ui.exportplotpackage.ExportPlotPackageController;
import gydatainput.ui.importplotpackage.ImportPlotPackageController;
import gydatainput.ui.plotpackage.PlotPackageController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

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
    @FXML ListView<Package> listImports;
    @FXML ListView<Package> listExports;
    @FXML Button btnUploadSelected;

    @FXML Button btnDownloadPackage;
    @FXML Button btnAddPackageToExport;
    @FXML Button btnRemovePackageFromExport;

    @FXML TextField txtFilterCompleted;
    @FXML Button btnOpenFileExportsFolder;
    @FXML ProgressBar progressBar;
    @FXML Label lblProgress;
    @FXML ProgressBar progressBarImport;
    @FXML Label lblProgressImport;

    // A list of all plot packages that are currently in the database.
    public ObservableList<Package> packages = FXCollections.observableArrayList();
    // A list of all plot packages that have not been submitted to the database.
    public ObservableList<Package> importPackages = FXCollections.observableArrayList();
    // A list of all plot packages that are currently in the export list.
    public ObservableList<Package> exportPackages = FXCollections.observableArrayList();

    // DO NOT REMOVE (This instantiates the database controller on startup and is required, despite not being referenced in this file)
    DatabaseController database = DatabaseController.getInstance();

    Path exportPath = Paths.get(System.getProperty("user.dir") + "/exports/");
    Path importPath = Paths.get(System.getProperty("user.dir") + "/imports/");

    static DoubleProperty progressUpdater = new SimpleDoubleProperty(0.0);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Bind the progress bar to a value
        progressBar.progressProperty().bind(progressUpdater);
        try {
            loadImportedPackages();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

                if(plotPackage.getPlot().getFromFields("PlotName").toString().toLowerCase().contains(lowerCaseFilter)) {
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
        listImports.setCellFactory(importListView -> new ImportPlotPackageController());

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
     * Gets the imported plot packages from the file /imports
     */
    @FXML
    private void loadImportedPackages() throws IOException, ParseException {
        listImports.getItems().clear();
        String[] pathnames;
        String importPath = System.getProperty("user.dir") + "/imports";
        File f = new File(importPath);

        pathnames = f.list();

        JSONParser parser = new JSONParser();
        // For each file in the directory..
        if (pathnames != null && pathnames.length > 0) {
            for(String pathname : pathnames) {
                // Convert to a JSON object.

                Object obj = parser.parse(new FileReader(importPath + "/" + pathname));
                JSONObject json = (JSONObject) obj;
                Package pkg = new Package(json, true);

                listImports.getItems().add(pkg);
            }
        }
    }

    /**
     * Opens the file with the System default file explorer.
     * @param
     */
    @FXML
    public void openExportFileLocation(ActionEvent actionEvent) throws IOException {
//        final String EXPLORER_EXE = "explorer.exe";
//        String command = "explorer.exe /select," + exportPath;
//        Runtime.getRuntime().exec(command);

        Desktop.getDesktop().open(new File(exportPath.toString()));
    }

    @FXML
    public void openImportFileLocation(ActionEvent actionEvent) throws IOException {
        Desktop.getDesktop().open(new File(importPath.toString()));
    }

    Service uploadService = new Service() {
        @Override
        protected Task createTask() {
            // TODO remove
            Random rd = new Random();

            Task uploadTask = new Task<Void>() {

                @Override
                protected Void call() throws Exception {
                    updateProgress(0, 100);
                    updateMessage("Uploading plot package...");
                    Package selected = listImports.getSelectionModel().getSelectedItem();

                    // Upload from each table.
                    JSONObject json = selected.getImportJSON();

                    // Upload each table
                    if (json.size() > 0) {
                        // Package
                        JSONArray packageFields = (JSONArray) json.get("fields");
                        if (packageFields.size() > 0) {
                            DatabaseHelper.uploadTableData("tblPackage", packageFields);
                        }

                        // Plot
                        JSONObject plotJSON = (JSONObject) json.get("Plot");
                        if (plotJSON != null && plotJSON.size() > 0 ) {
                            JSONArray plotFields = (JSONArray) plotJSON.get("fields");
                            DatabaseHelper.uploadTableData("tblPlot", plotFields);

                            // Note
                            JSONArray notes = (JSONArray) plotJSON.get("Note");
                            if (notes  != null && !notes.isEmpty()) {
                                Iterator<JSONObject> noteKeys = notes.iterator();
                                while(noteKeys.hasNext()) {
                                    JSONObject noteKey = (JSONObject) noteKeys.next();
                                    JSONArray noteKeyFields = (JSONArray) noteKey.get("fields");
                                    // TODO Note does not have fields
                                    DatabaseHelper.uploadTableData("tblNote", noteKeyFields);
                                }
                            }

                            // Note Fixup
                            JSONArray noteFixups = (JSONArray) plotJSON.get("NoteFixup");
                            if (noteFixups != null && !noteFixups.isEmpty()) {
                                Iterator<JSONObject> noteFixupKeys = noteFixups.iterator();
                                while(noteFixupKeys.hasNext()) {
                                    JSONObject noteFixupKey = (JSONObject) noteFixupKeys.next();
                                    JSONArray noteFixupFields = (JSONArray) noteFixupKey.get("fields");
                                    DatabaseHelper.uploadTableData("tblNoteFixup", noteFixupFields);
                                }
                            }

                            // TODO remove this
                            Thread.sleep((long) 1800);
                            updateProgress(rd.nextInt(30-10), 100);

                            // NotePlot
                            JSONObject notePlot = (JSONObject) plotJSON.get("NotePlot");
                            if (notePlot != null && notePlot.size() > 0) {
                                JSONArray notePlotFields = (JSONArray) notePlot.get("fields");
                                // TODO Note Plot does not have fields
                                DatabaseHelper.uploadTableData("tblNotePlot", notePlotFields);
                            }

                            // Site Perm Rest
                            JSONObject sitePermRest = (JSONObject) plotJSON.get("SitePermRest");
                            if (sitePermRest != null && sitePermRest.size() > 0) {
                                JSONArray sitePermRestFields = (JSONArray) sitePermRest.get("fields");
                                // TODO SitePermRest does not have fields
                                DatabaseHelper.uploadTableData("tblNotePlot", sitePermRestFields);
                            }

                            // Site Perm Plot
                            JSONObject sitePermPlot = (JSONObject) plotJSON.get("SitePermPlot");
                            if (sitePermPlot != null && sitePermPlot.size() > 0) {
                                JSONArray sitePermPlotFields = (JSONArray) sitePermPlot.get("fields");
                                // TODO SitePermPlot does not have fields.
                                DatabaseHelper.uploadTableData("tblSitePermPlot", sitePermPlotFields);
                            }

                            // Loc Plot
                            JSONObject locPlot = (JSONObject) plotJSON.get("LocPlot");
                            if (locPlot != null && locPlot.size() > 0) {
                                JSONArray locPlotFields = (JSONArray) locPlot.get("fields");
                                // TODO LocPlot does not have fields.
                                DatabaseHelper.uploadTableData("tblLocPlot", locPlotFields);
                            }

                            // Plot Access
                            JSONArray plotAccess = (JSONArray) plotJSON.get("PlotAccess");
                            if (plotAccess != null && !plotAccess.isEmpty()) {
                                Iterator<JSONObject> plotAccessKeys = plotAccess.iterator();
                                while(plotAccessKeys.hasNext()) {
                                    JSONObject plotAccessKey = (JSONObject) plotAccessKeys.next();
                                    JSONArray plotAccessFields = (JSONArray) plotAccessKey.get("fields");
                                    // TODO PlotAccess does not have fields
                                    DatabaseHelper.uploadTableData("tblPlotAccess", plotAccessFields);
                                }
                            }

                            // Loc Coord
                            JSONArray locCoord = (JSONArray) plotJSON.get("LocCoord");
                            if (locCoord != null && !locCoord.isEmpty()) {
                                Iterator<JSONObject> locCoordKeys = locCoord.iterator();
                                while(locCoordKeys.hasNext()) {

                                    JSONObject locCoordKey = (JSONObject) locCoordKeys.next();
                                    JSONArray plotAccessFields = (JSONArray) locCoordKey.get("fields");
                                    DatabaseHelper.uploadTableData("tblLocCoord", plotAccessFields);
                                }
                            }

                            // Stand Info Plot
                            handleUpload("StandInfoPlot", plotJSON);

                            // Stand Info Distb
                            handleArrayUpload("StandInfoDistb", plotJSON);

                            // Stand Info Treat
                            handleArrayUpload("StandInfoTreat", plotJSON);

                            // Stand Info Compr
                            handleArrayUpload("StandInfoCompr", plotJSON);

                            // Plot Map Mort
                            handleUpload("PlotMapMort", plotJSON);

                            // Plot Map Growth Plot
                            JSONArray plotMapGrowthPlot = (JSONArray) plotJSON.get("PlotMapGrowthPlot");
                            if (plotMapGrowthPlot != null && !plotMapGrowthPlot.isEmpty()) {
                                Iterator<JSONObject> plotMapGrowthPlotKeys = plotMapGrowthPlot.iterator();
                                while(plotMapGrowthPlotKeys.hasNext()) {
                                    // Plot Map Growth Plot fields
                                    JSONObject plotMapGrowthPlotKey = (JSONObject) plotMapGrowthPlotKeys.next();
                                    JSONArray plotMapGrowthPlotFields = (JSONArray) plotMapGrowthPlotKey.get("fields");
                                    DatabaseHelper.uploadTableData("tblPlotMapGrowthPlot", plotMapGrowthPlotFields);

                                    handleArrayUpload("Tree", plotMapGrowthPlotKey);
                                }
                            }

                            //TODO remove this
                            Thread.sleep((long) 1400);
                            updateProgress(rd.nextInt(60-45), 100);

                            // Soil Sample
                            JSONArray soilSample = (JSONArray) plotJSON.get("SoilSample");
                            if (soilSample != null && !soilSample.isEmpty()) {
                                Iterator<JSONObject> soilSampleKeys = soilSample.iterator();
                                while(soilSampleKeys.hasNext()) {
                                    // Soil Sample fields
                                    JSONObject soilSampleKey = (JSONObject) soilSampleKeys.next();
                                    JSONArray soilSampleFields = (JSONArray) soilSampleKey.get("fields");
                                    DatabaseHelper.uploadTableData("tblLocCoord", soilSampleFields);

                                    // SoilDepMode
                                    handleArrayUpload("SoilDepMode", soilSampleKey);
                                    // SoilPhoto
                                    handleArrayUpload("SoilPhoto", soilSampleKey);
                                    // SoilHor
                                    handleArrayUpload("SoilHor", soilSampleKey);
                                }
                            }

                            // Soil Site Macro Meso Micro
                            handleUpload("SoilPlot", plotJSON);
                            handleArrayUpload("SoilGrowthPlot", plotJSON);

                            // Specialist
                            handleArrayUpload("SpecAssoc", plotJSON);
                        }

                        // Visits
                        JSONArray visits = (JSONArray) json.get("tblVisit");
                        if (!visits.isEmpty()) {
                            Iterator<JSONObject> visitKeys = visits.iterator();
                            while(visitKeys.hasNext()) {
                                // Visit
                                JSONObject key = (JSONObject) visitKeys.next();
                                JSONArray visitFields = (JSONArray) key.get("fields");
                                DatabaseHelper.uploadTableData("tblVisit", visitFields);

                                // StandInfoHeader
                                handleUpload("StandInfoHeader", key);
                                // PhotoHeader
                                JSONObject photoHeaderJSON = (JSONObject) key.get("tblPhotoHeader");
                                if (photoHeaderJSON != null && photoHeaderJSON.size() > 0) {
                                    // PhotoHeader fields
                                    JSONArray fields = (JSONArray) photoHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblPhotoHeader", fields);

                                    // PhotoRequired
                                    handleArrayUpload("PhotoRequired", photoHeaderJSON);

                                    // PhotoFeature
                                    handleArrayUpload("PhotoFeature", photoHeaderJSON);
                                }

                                // VegHeader
                                JSONObject vegHeaderJSON = (JSONObject) key.get("tblVegHeader");
                                if (vegHeaderJSON != null && vegHeaderJSON.size() > 0) {
                                    // VegHeader fields
                                    JSONArray fields = (JSONArray) vegHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblVegHeader", fields);

                                    // VegVType
                                    handleArrayUpload("VegVType", vegHeaderJSON);

                                    // VegPlot
                                    JSONArray vegPlot = (JSONArray) vegHeaderJSON.get("tblVegPlot");
                                    if (vegPlot != null && !vegPlot.isEmpty()) {
                                        Iterator<JSONObject> vegPlotKeys = vegPlot.iterator();
                                        while(vegPlotKeys.hasNext()) {
                                            // VegPlot fields
                                            JSONObject vegPlotKey = (JSONObject) vegPlotKeys.next();
                                            JSONArray vegPlotFields = (JSONArray) vegPlotKey.get("fields");
                                            DatabaseHelper.uploadTableData("tblVegPlot", vegPlotFields);

                                            // VegCover
                                            handleArrayUpload("VegCover", vegPlotKey);

                                            // VegRegen
                                            handleArrayUpload("VegRegen", vegPlotKey);

                                            // VegSpecPres
                                            handleArrayUpload("VegSpecPres", vegPlotKey);

                                            // VegShrubPres
                                            handleArrayUpload("VegShrubPres", vegPlotKey);
                                        }
                                    }
                                }

                                // TreeHeader
                                JSONObject treeHeaderJSON = (JSONObject) key.get("tblTreeHeader");
                                if (treeHeaderJSON != null && treeHeaderJSON.size() > 0) {
                                    // PhotoHeader fields
                                    JSONArray fields = (JSONArray) treeHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblTreeHeader", fields);

                                    // TreeGrowthPlot
                                    JSONArray treeGrowthPlot = (JSONArray) treeHeaderJSON.get("tblTreeGrowthPlot");
                                    if (treeGrowthPlot != null && !treeGrowthPlot.isEmpty()) {
                                        Iterator<JSONObject> treeGrowthPlotKeys = treeGrowthPlot.iterator();
                                        while(treeGrowthPlotKeys.hasNext()) {
                                            // TreeGrowthPlot fields
                                            JSONObject treeGrowthPlotKey = (JSONObject) treeGrowthPlotKeys.next();
                                            JSONArray treeGrowthPlotFields = (JSONArray) treeGrowthPlotKey.get("fields");
                                            DatabaseHelper.uploadTableData("tblTreeGrowthPlot", treeGrowthPlotFields);

                                            // TreeMsr
                                            JSONArray treeMsr = (JSONArray) treeHeaderJSON.get("tblTreeMsr");
                                            if (treeMsr != null && !treeMsr.isEmpty()) {
                                                Iterator<JSONObject> treeMsrKeys = treeMsr.iterator();
                                                while(treeMsrKeys.hasNext()) {
                                                    // TreeMsr fields
                                                    JSONObject treeMsrKey = (JSONObject) treeMsrKeys.next();
                                                    JSONArray treeMsrFields = (JSONArray) treeMsrKey.get("fields");
                                                    DatabaseHelper.uploadTableData("tblTreeMsr", treeMsrFields);

                                                    // TreeMissed
                                                    handleUpload("TreeMissed", treeMsrKey);

                                                    // TreeDefm
                                                    handleArrayUpload("TreeDefm", treeMsrKey);

                                                    // TreeCav
                                                    handleArrayUpload("TreeCav", treeMsrKey);

                                                    // Ht
                                                    handleUpload("Ht", treeMsrKey);
                                                }
                                            }
                                        }
                                    }
                                }

                                // HtHeader
                                JSONObject htHeaderJSON = (JSONObject) key.get("tblHtHeader");
                                if (htHeaderJSON != null && htHeaderJSON.size() > 0) {
                                    // PlotMapHeader fields
                                    JSONArray fields = (JSONArray) htHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblHtHeader", fields);

                                    // Ht
                                    handleArrayUpload("Ht", htHeaderJSON);
                                }

                                // DWDHeader
                                JSONObject dwdHeaderJSON = (JSONObject) key.get("tblDWDHeader");
                                if (dwdHeaderJSON != null && dwdHeaderJSON.size() > 0) {
                                    // DWDHeader fields
                                    JSONArray fields = (JSONArray) dwdHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblDWDHeader", fields);

                                    // DWDLine
                                    JSONArray dwdLine = (JSONArray) dwdHeaderJSON.get("tblDWDLine");
                                    if (dwdLine != null && !dwdLine.isEmpty()) {
                                        Iterator<JSONObject> dwdLineKeys = dwdLine.iterator();
                                        while(dwdLineKeys.hasNext()) {
                                            // DWDLine fields
                                            JSONObject dwdLineKey = (JSONObject) dwdLineKeys.next();
                                            JSONArray dwdLineFields = (JSONArray) dwdLineKey.get("fields");
                                            DatabaseHelper.uploadTableData("tblDWDLine", dwdLineFields);

                                            // DWDIntersect
                                            handleArrayUpload("DWDIntersect", dwdLineKey);

                                            // DWDStump
                                            handleArrayUpload("DWDStump", dwdLineKey);

                                            // DWDAccum
                                            handleArrayUpload("DWDAccum", dwdLineKey);
                                        }
                                    }

                                    // DWD
                                    JSONArray dwd = (JSONArray) treeHeaderJSON.get("tblDWD");
                                    if (dwd != null && !dwd.isEmpty()) {
                                        Iterator<JSONObject> dwdKeys = dwd.iterator();
                                        while(dwdKeys.hasNext()) {
                                            // DWD fields
                                            JSONObject dwdKey = (JSONObject) dwdKeys.next();
                                            JSONArray dwdFields = (JSONArray) dwdKey.get("fields");
                                            DatabaseHelper.uploadTableData("tblDWD", dwdFields);

                                            // DWDIntersect
                                            handleArrayUpload("DWDIntersect", dwdKey);
                                        }
                                    }
                                }

                                // PlotMapHeader
                                handleUpload("PlotMapHeader", key);

                                // StkgHeader
                                JSONObject stkgHeaderJSON = (JSONObject) key.get("tblStkgHeader");
                                if (stkgHeaderJSON != null && stkgHeaderJSON.size() > 0) {
                                    // StkgHeader fields
                                    JSONArray fields = (JSONArray) stkgHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblStkgHeader", fields);

                                    // StkgLine
                                    JSONArray stkgLine = (JSONArray) treeHeaderJSON.get("tblStkgLine");
                                    if (stkgLine != null && !stkgLine.isEmpty()) {
                                        Iterator<JSONObject> stkgLineKeys = stkgLine.iterator();
                                        while(stkgLineKeys.hasNext()) {
                                            // StkgLine fields
                                            JSONObject stkgLineKey = (JSONObject) stkgLineKeys.next();
                                            JSONArray stkgLineFields = (JSONArray) stkgLineKey.get("fields");
                                            DatabaseHelper.uploadTableData("tblStkgLine", stkgLineFields);

                                            // Stkg
                                            handleArrayUpload("Stkg", stkgLineKey);
                                        }
                                    }
                                }

                                // MortHeader
                                JSONObject mortHeaderJSON = (JSONObject) key.get("tblMortHeader");
                                if (mortHeaderJSON != null && mortHeaderJSON.size() > 0) {
                                    // MortHeader fields
                                    JSONArray fields = (JSONArray) mortHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblMortHeader", fields);

                                    // MortTreeMsr
                                    JSONArray mortTreeMsr = (JSONArray) mortHeaderJSON.get("tblMortTreeMsr");
                                    if (mortTreeMsr != null && !mortTreeMsr.isEmpty()) {
                                        Iterator<JSONObject> mortTreeMsrKeys = mortTreeMsr.iterator();
                                        while(mortTreeMsrKeys.hasNext()) {
                                            // MortTreeMsr fields
                                            JSONObject mortTreeMsrKey = (JSONObject) mortTreeMsrKeys.next();
                                            JSONArray mortTreeMsrFields = (JSONArray) mortTreeMsrKey.get("fields");
                                            DatabaseHelper.uploadTableData("tblMortTreeMsr", mortTreeMsrFields);

                                            // MortTree
                                            handleArrayUpload("MortTree", mortTreeMsrKey);
                                        }
                                    }
                                }

                                // AgeHeader
                                JSONObject ageHeaderJSON = (JSONObject) key.get("tblAgeHeader");
                                if (ageHeaderJSON != null && ageHeaderJSON.size() > 0) {
                                    // AgeHeader fields
                                    JSONArray fields = (JSONArray) ageHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblAgeHeader", fields);

                                    // AgeTree
                                    JSONArray ageTree = (JSONArray) ageHeaderJSON.get("tblAgeTree");
                                    if (ageTree != null && !ageTree.isEmpty()) {
                                        Iterator<JSONObject> ageTreeKeys = ageTree.iterator();
                                        while(ageTreeKeys.hasNext()) {
                                            // AgeTree fields
                                            JSONObject ageTreeKey = (JSONObject) ageTreeKeys.next();
                                            JSONArray ageTreeFields = (JSONArray) ageTreeKey.get("fields");
                                            DatabaseHelper.uploadTableData("tblAgeTree", ageTreeFields);

                                            // AgeSample
                                            handleArrayUpload("AgeSample", ageTreeKey);
                                        }
                                    }
                                }

                                // SoilEcositeHeader
                                JSONObject soilEcositeHeaderJSON = (JSONObject) key.get("tblSoilEcositeHeader");
                                if (soilEcositeHeaderJSON != null && soilEcositeHeaderJSON.size() > 0) {
                                    // SoilEcositeHeader fields
                                    JSONArray fields = (JSONArray) soilEcositeHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblSoilEcositeHeader", fields);

                                    // SoilEcosite
                                    handleArrayUpload("SoilEcosite", soilEcositeHeaderJSON);
                                }

                                // SoilHeader
                                JSONObject soilHeaderJSON = (JSONObject) key.get("tblSoilHeader");
                                if (soilHeaderJSON != null && soilHeaderJSON.size() > 0) {
                                    // SoilHeader fields
                                    JSONArray fields = (JSONArray) soilHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblSoilHeader", fields);

                                    // SoilForestFloor
                                    handleArrayUpload("SoilForestFloor", soilHeaderJSON);

                                    // SoilGroundCover
                                    handleArrayUpload("SoilGroundCover", soilHeaderJSON);
                                }

                                // SelfQAHeader
                                JSONObject selfQAHeaderJSON = (JSONObject) key.get("tblSelfQAHeader");
                                if (selfQAHeaderJSON != null && selfQAHeaderJSON.size() > 0) {
                                    // SelfQAHeader fields
                                    JSONArray fields = (JSONArray) selfQAHeaderJSON.get("fields");
                                    DatabaseHelper.uploadTableData("tblSelfQAHeader", fields);

                                    // SelfQAHt
                                    handleArrayUpload("SelfQAHt", selfQAHeaderJSON);

                                    // SelfQATree
                                    JSONArray selfQATree = (JSONArray) ageHeaderJSON.get("tblSelfQATree");
                                    if (selfQATree != null && !selfQATree.isEmpty()) {
                                        Iterator<JSONObject> selfQATreeKeys = selfQATree.iterator();
                                        while(selfQATreeKeys.hasNext()) {
                                            // AgeTree fields
                                            JSONObject selfQATreeKey = (JSONObject) selfQATreeKeys.next();
                                            JSONArray selfQATreeFields = (JSONArray) selfQATreeKey.get("fields");
                                            DatabaseHelper.uploadTableData("tblSelfQATree", selfQATreeFields);

                                            // SelfQADeform
                                            handleArrayUpload("SelfQADeform", selfQATreeKey);
                                        }
                                    }
                                }
                            }
                        }

                        // SpecGYHeader
                        JSONObject specGYHeaderJSON = (JSONObject) json.get("tblSpecGYHeader");
                        if (specGYHeaderJSON != null && specGYHeaderJSON.size() > 0) {
                            JSONArray specGYHeaderFields = (JSONArray) specGYHeaderJSON.get("fields");
                            DatabaseHelper.uploadTableData("tblSpecGYHeader", specGYHeaderFields);
                        }
                    }

                    // TODO remove
                    Thread.sleep((long) 1200);
                    updateProgress(100, 100);
                    updateMessage("Done");
                    return null;
                }
            };

            uploadTask.setOnSucceeded((e) -> {
                btnUploadSelected.disableProperty().set(false);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Plot Package uploaded successfully");
                alert.showAndWait();
            });

            progressBarImport.progressProperty().bind(uploadTask.progressProperty());
            lblProgressImport.textProperty().bind(uploadTask.messageProperty());

            return uploadTask;
        }
    };



    @FXML
    public void uploadPlotPackage(ActionEvent event) {
        btnUploadSelected.disableProperty().set(true);

        // Start the export service
        if (uploadService.isRunning()) {
            uploadService.reset();
            uploadService.start();
        } else {
            uploadService.restart();
        }
    }

    /**
     * Opens the file with the System default file explorer.
     * @param
     */
    @FXML
    public void uploadPlotPackageOLD(ActionEvent actionEvent) throws IOException, InterruptedException {
        Package selected = listImports.getSelectionModel().getSelectedItem();

        // Upload from each table.
        JSONObject json = selected.getImportJSON();

        // Upload each table
        if (json.size() > 0) {
            // Package
            JSONArray packageFields = (JSONArray) json.get("fields");
            if (packageFields.size() > 0) {
                DatabaseHelper.uploadTableData("tblPackage", packageFields);
            }

            // Plot
            JSONObject plotJSON = (JSONObject) json.get("Plot");
            if (plotJSON != null && plotJSON.size() > 0 ) {
                JSONArray plotFields = (JSONArray) plotJSON.get("fields");
                DatabaseHelper.uploadTableData("tblPlot", plotFields);

                // Note
                JSONArray notes = (JSONArray) plotJSON.get("Note");
                if (notes  != null && !notes.isEmpty()) {
                    Iterator<JSONObject> noteKeys = notes.iterator();
                    while(noteKeys.hasNext()) {
                        JSONObject noteKey = (JSONObject) noteKeys.next();
                        JSONArray noteKeyFields = (JSONArray) noteKey.get("fields");
                        // TODO Note does not have fields
                        DatabaseHelper.uploadTableData("tblNote", noteKeyFields);
                    }
                }

                // Note Fixup
                JSONArray noteFixups = (JSONArray) plotJSON.get("NoteFixup");
                if (noteFixups != null && !noteFixups.isEmpty()) {
                    Iterator<JSONObject> noteFixupKeys = noteFixups.iterator();
                    while(noteFixupKeys.hasNext()) {
                        JSONObject noteFixupKey = (JSONObject) noteFixupKeys.next();
                        JSONArray noteFixupFields = (JSONArray) noteFixupKey.get("fields");
                        DatabaseHelper.uploadTableData("tblNoteFixup", noteFixupFields);
                    }
                }

                // NotePlot
                JSONObject notePlot = (JSONObject) plotJSON.get("NotePlot");
                if (notePlot != null && notePlot.size() > 0) {
                    JSONArray notePlotFields = (JSONArray) notePlot.get("fields");
                    // TODO Note Plot does not have fields
                    DatabaseHelper.uploadTableData("tblNotePlot", notePlotFields);
                }

                // Site Perm Rest
                JSONObject sitePermRest = (JSONObject) plotJSON.get("SitePermRest");
                if (sitePermRest != null && sitePermRest.size() > 0) {
                    JSONArray sitePermRestFields = (JSONArray) sitePermRest.get("fields");
                    // TODO SitePermRest does not have fields
                    DatabaseHelper.uploadTableData("tblNotePlot", sitePermRestFields);
                }

                // Site Perm Plot
                JSONObject sitePermPlot = (JSONObject) plotJSON.get("SitePermPlot");
                if (sitePermPlot != null && sitePermPlot.size() > 0) {
                    JSONArray sitePermPlotFields = (JSONArray) sitePermPlot.get("fields");
                    // TODO SitePermPlot does not have fields.
                    DatabaseHelper.uploadTableData("tblSitePermPlot", sitePermPlotFields);
                }

                // Loc Plot
                JSONObject locPlot = (JSONObject) plotJSON.get("LocPlot");
                if (locPlot != null && locPlot.size() > 0) {
                    JSONArray locPlotFields = (JSONArray) locPlot.get("fields");
                    // TODO LocPlot does not have fields.
                    DatabaseHelper.uploadTableData("tblLocPlot", locPlotFields);
                }

                // Plot Access
                JSONArray plotAccess = (JSONArray) plotJSON.get("PlotAccess");
                if (plotAccess != null && !plotAccess.isEmpty()) {
                    Iterator<JSONObject> plotAccessKeys = plotAccess.iterator();
                    while(plotAccessKeys.hasNext()) {
                        JSONObject plotAccessKey = (JSONObject) plotAccessKeys.next();
                        JSONArray plotAccessFields = (JSONArray) plotAccessKey.get("fields");
                        // TODO PlotAccess does not have fields
                        DatabaseHelper.uploadTableData("tblPlotAccess", plotAccessFields);
                    }
                }

                // Loc Coord
                JSONArray locCoord = (JSONArray) plotJSON.get("LocCoord");
                if (locCoord != null && !locCoord.isEmpty()) {
                    Iterator<JSONObject> locCoordKeys = locCoord.iterator();
                    while(locCoordKeys.hasNext()) {

                        JSONObject locCoordKey = (JSONObject) locCoordKeys.next();
                        JSONArray plotAccessFields = (JSONArray) locCoordKey.get("fields");
                        DatabaseHelper.uploadTableData("tblLocCoord", plotAccessFields);
                    }
                }

                // Stand Info Plot
                handleUpload("StandInfoPlot", plotJSON);

                // Stand Info Distb
                handleArrayUpload("StandInfoDistb", plotJSON);

                // Stand Info Treat
                handleArrayUpload("StandInfoTreat", plotJSON);

                // Stand Info Compr
                handleArrayUpload("StandInfoCompr", plotJSON);

                // Plot Map Mort
                handleUpload("PlotMapMort", plotJSON);

                // Plot Map Growth Plot
                JSONArray plotMapGrowthPlot = (JSONArray) plotJSON.get("PlotMapGrowthPlot");
                if (plotMapGrowthPlot != null && !plotMapGrowthPlot.isEmpty()) {
                    Iterator<JSONObject> plotMapGrowthPlotKeys = plotMapGrowthPlot.iterator();
                    while(plotMapGrowthPlotKeys.hasNext()) {
                        // Plot Map Growth Plot fields
                        JSONObject plotMapGrowthPlotKey = (JSONObject) plotMapGrowthPlotKeys.next();
                        JSONArray plotMapGrowthPlotFields = (JSONArray) plotMapGrowthPlotKey.get("fields");
                        DatabaseHelper.uploadTableData("tblPlotMapGrowthPlot", plotMapGrowthPlotFields);

                        handleArrayUpload("Tree", plotMapGrowthPlotKey);
                    }
                }

                // Soil Sample
                JSONArray soilSample = (JSONArray) plotJSON.get("SoilSample");
                if (soilSample != null && !soilSample.isEmpty()) {
                    Iterator<JSONObject> soilSampleKeys = soilSample.iterator();
                    while(soilSampleKeys.hasNext()) {
                        // Soil Sample fields
                        JSONObject soilSampleKey = (JSONObject) soilSampleKeys.next();
                        JSONArray soilSampleFields = (JSONArray) soilSampleKey.get("fields");
                        DatabaseHelper.uploadTableData("tblLocCoord", soilSampleFields);

                        // SoilDepMode
                        handleArrayUpload("SoilDepMode", soilSampleKey);
                        // SoilPhoto
                        handleArrayUpload("SoilPhoto", soilSampleKey);
                        // SoilHor
                        handleArrayUpload("SoilHor", soilSampleKey);
                    }
                }

                // Soil Site Macro Meso Micro
                handleUpload("SoilPlot", plotJSON);
                handleArrayUpload("SoilGrowthPlot", plotJSON);

                // Specialist
                handleArrayUpload("SpecAssoc", plotJSON);
            }

            // Visits
            JSONArray visits = (JSONArray) json.get("tblVisit");
            if (!visits.isEmpty()) {
                Iterator<JSONObject> visitKeys = visits.iterator();
                while(visitKeys.hasNext()) {
                    // Visit
                    JSONObject key = (JSONObject) visitKeys.next();
                    JSONArray visitFields = (JSONArray) key.get("fields");
                    DatabaseHelper.uploadTableData("tblVisit", visitFields);

                    // StandInfoHeader
                    handleUpload("StandInfoHeader", key);
                    // PhotoHeader
                    JSONObject photoHeaderJSON = (JSONObject) key.get("tblPhotoHeader");
                    if (photoHeaderJSON != null && photoHeaderJSON.size() > 0) {
                        // PhotoHeader fields
                        JSONArray fields = (JSONArray) photoHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblPhotoHeader", fields);

                        // PhotoRequired
                        handleArrayUpload("PhotoRequired", photoHeaderJSON);

                        // PhotoFeature
                        handleArrayUpload("PhotoFeature", photoHeaderJSON);
                    }

                    // VegHeader
                    JSONObject vegHeaderJSON = (JSONObject) key.get("tblVegHeader");
                    if (vegHeaderJSON != null && vegHeaderJSON.size() > 0) {
                        // VegHeader fields
                        JSONArray fields = (JSONArray) vegHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblVegHeader", fields);

                        // VegVType
                        handleArrayUpload("VegVType", vegHeaderJSON);

                        // VegPlot
                        JSONArray vegPlot = (JSONArray) vegHeaderJSON.get("tblVegPlot");
                        if (vegPlot != null && !vegPlot.isEmpty()) {
                            Iterator<JSONObject> vegPlotKeys = vegPlot.iterator();
                            while(vegPlotKeys.hasNext()) {
                                // VegPlot fields
                                JSONObject vegPlotKey = (JSONObject) vegPlotKeys.next();
                                JSONArray vegPlotFields = (JSONArray) vegPlotKey.get("fields");
                                DatabaseHelper.uploadTableData("tblVegPlot", vegPlotFields);

                                // VegCover
                                handleArrayUpload("VegCover", vegPlotKey);

                                // VegRegen
                                handleArrayUpload("VegRegen", vegPlotKey);

                                // VegSpecPres
                                handleArrayUpload("VegSpecPres", vegPlotKey);

                                // VegShrubPres
                                handleArrayUpload("VegShrubPres", vegPlotKey);
                            }
                        }
                    }

                    // TreeHeader
                    JSONObject treeHeaderJSON = (JSONObject) key.get("tblTreeHeader");
                    if (treeHeaderJSON != null && treeHeaderJSON.size() > 0) {
                        // PhotoHeader fields
                        JSONArray fields = (JSONArray) treeHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblTreeHeader", fields);

                        // TreeGrowthPlot
                        JSONArray treeGrowthPlot = (JSONArray) treeHeaderJSON.get("tblTreeGrowthPlot");
                        if (treeGrowthPlot != null && !treeGrowthPlot.isEmpty()) {
                            Iterator<JSONObject> treeGrowthPlotKeys = treeGrowthPlot.iterator();
                            while(treeGrowthPlotKeys.hasNext()) {
                                // TreeGrowthPlot fields
                                JSONObject treeGrowthPlotKey = (JSONObject) treeGrowthPlotKeys.next();
                                JSONArray treeGrowthPlotFields = (JSONArray) treeGrowthPlotKey.get("fields");
                                DatabaseHelper.uploadTableData("tblTreeGrowthPlot", treeGrowthPlotFields);

                                // TreeMsr
                                JSONArray treeMsr = (JSONArray) treeHeaderJSON.get("tblTreeMsr");
                                if (treeMsr != null && !treeMsr.isEmpty()) {
                                    Iterator<JSONObject> treeMsrKeys = treeMsr.iterator();
                                    while(treeMsrKeys.hasNext()) {
                                        // TreeMsr fields
                                        JSONObject treeMsrKey = (JSONObject) treeMsrKeys.next();
                                        JSONArray treeMsrFields = (JSONArray) treeMsrKey.get("fields");
                                        DatabaseHelper.uploadTableData("tblTreeMsr", treeMsrFields);

                                        // TreeMissed
                                        handleUpload("TreeMissed", treeMsrKey);

                                        // TreeDefm
                                        handleArrayUpload("TreeDefm", treeMsrKey);

                                        // TreeCav
                                        handleArrayUpload("TreeCav", treeMsrKey);

                                        // Ht
                                        handleUpload("Ht", treeMsrKey);
                                    }
                                }
                            }
                        }
                    }

                    // HtHeader
                    JSONObject htHeaderJSON = (JSONObject) key.get("tblHtHeader");
                    if (htHeaderJSON != null && htHeaderJSON.size() > 0) {
                        // PlotMapHeader fields
                        JSONArray fields = (JSONArray) htHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblHtHeader", fields);

                        // Ht
                        handleArrayUpload("Ht", htHeaderJSON);
                    }

                    // DWDHeader
                    JSONObject dwdHeaderJSON = (JSONObject) key.get("tblDWDHeader");
                    if (dwdHeaderJSON != null && dwdHeaderJSON.size() > 0) {
                        // DWDHeader fields
                        JSONArray fields = (JSONArray) dwdHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblDWDHeader", fields);

                        // DWDLine
                        JSONArray dwdLine = (JSONArray) dwdHeaderJSON.get("tblDWDLine");
                        if (dwdLine != null && !dwdLine.isEmpty()) {
                            Iterator<JSONObject> dwdLineKeys = dwdLine.iterator();
                            while(dwdLineKeys.hasNext()) {
                                // DWDLine fields
                                JSONObject dwdLineKey = (JSONObject) dwdLineKeys.next();
                                JSONArray dwdLineFields = (JSONArray) dwdLineKey.get("fields");
                                DatabaseHelper.uploadTableData("tblDWDLine", dwdLineFields);

                                // DWDIntersect
                                handleArrayUpload("DWDIntersect", dwdLineKey);

                                // DWDStump
                                handleArrayUpload("DWDStump", dwdLineKey);

                                // DWDAccum
                                handleArrayUpload("DWDAccum", dwdLineKey);
                            }
                        }

                        // DWD
                        JSONArray dwd = (JSONArray) treeHeaderJSON.get("tblDWD");
                        if (dwd != null && !dwd.isEmpty()) {
                            Iterator<JSONObject> dwdKeys = dwd.iterator();
                            while(dwdKeys.hasNext()) {
                                // DWD fields
                                JSONObject dwdKey = (JSONObject) dwdKeys.next();
                                JSONArray dwdFields = (JSONArray) dwdKey.get("fields");
                                DatabaseHelper.uploadTableData("tblDWD", dwdFields);

                                // DWDIntersect
                                handleArrayUpload("DWDIntersect", dwdKey);
                            }
                        }
                    }

                    // PlotMapHeader
                    handleUpload("PlotMapHeader", key);

                    // StkgHeader
                    JSONObject stkgHeaderJSON = (JSONObject) key.get("tblStkgHeader");
                    if (stkgHeaderJSON != null && stkgHeaderJSON.size() > 0) {
                        // StkgHeader fields
                        JSONArray fields = (JSONArray) stkgHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblStkgHeader", fields);

                        // StkgLine
                        JSONArray stkgLine = (JSONArray) treeHeaderJSON.get("tblStkgLine");
                        if (stkgLine != null && !stkgLine.isEmpty()) {
                            Iterator<JSONObject> stkgLineKeys = stkgLine.iterator();
                            while(stkgLineKeys.hasNext()) {
                                // StkgLine fields
                                JSONObject stkgLineKey = (JSONObject) stkgLineKeys.next();
                                JSONArray stkgLineFields = (JSONArray) stkgLineKey.get("fields");
                                DatabaseHelper.uploadTableData("tblStkgLine", stkgLineFields);

                                // Stkg
                                handleArrayUpload("Stkg", stkgLineKey);
                            }
                        }
                    }

                    // MortHeader
                    JSONObject mortHeaderJSON = (JSONObject) key.get("tblMortHeader");
                    if (mortHeaderJSON != null && mortHeaderJSON.size() > 0) {
                        // MortHeader fields
                        JSONArray fields = (JSONArray) mortHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblMortHeader", fields);

                        // MortTreeMsr
                        JSONArray mortTreeMsr = (JSONArray) mortHeaderJSON.get("tblMortTreeMsr");
                        if (mortTreeMsr != null && !mortTreeMsr.isEmpty()) {
                            Iterator<JSONObject> mortTreeMsrKeys = mortTreeMsr.iterator();
                            while(mortTreeMsrKeys.hasNext()) {
                                // MortTreeMsr fields
                                JSONObject mortTreeMsrKey = (JSONObject) mortTreeMsrKeys.next();
                                JSONArray mortTreeMsrFields = (JSONArray) mortTreeMsrKey.get("fields");
                                DatabaseHelper.uploadTableData("tblMortTreeMsr", mortTreeMsrFields);

                                // MortTree
                                handleArrayUpload("MortTree", mortTreeMsrKey);
                            }
                        }
                    }

                    // AgeHeader
                    JSONObject ageHeaderJSON = (JSONObject) key.get("tblAgeHeader");
                    if (ageHeaderJSON != null && ageHeaderJSON.size() > 0) {
                        // AgeHeader fields
                        JSONArray fields = (JSONArray) ageHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblAgeHeader", fields);

                        // AgeTree
                        JSONArray ageTree = (JSONArray) ageHeaderJSON.get("tblAgeTree");
                        if (ageTree != null && !ageTree.isEmpty()) {
                            Iterator<JSONObject> ageTreeKeys = ageTree.iterator();
                            while(ageTreeKeys.hasNext()) {
                                // AgeTree fields
                                JSONObject ageTreeKey = (JSONObject) ageTreeKeys.next();
                                JSONArray ageTreeFields = (JSONArray) ageTreeKey.get("fields");
                                DatabaseHelper.uploadTableData("tblAgeTree", ageTreeFields);

                                // AgeSample
                                handleArrayUpload("AgeSample", ageTreeKey);
                            }
                        }
                    }

                    // SoilEcositeHeader
                    JSONObject soilEcositeHeaderJSON = (JSONObject) key.get("tblSoilEcositeHeader");
                    if (soilEcositeHeaderJSON != null && soilEcositeHeaderJSON.size() > 0) {
                        // SoilEcositeHeader fields
                        JSONArray fields = (JSONArray) soilEcositeHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblSoilEcositeHeader", fields);

                        // SoilEcosite
                        handleArrayUpload("SoilEcosite", soilEcositeHeaderJSON);
                    }

                    // SoilHeader
                    JSONObject soilHeaderJSON = (JSONObject) key.get("tblSoilHeader");
                    if (soilHeaderJSON != null && soilHeaderJSON.size() > 0) {
                        // SoilHeader fields
                        JSONArray fields = (JSONArray) soilHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblSoilHeader", fields);

                        // SoilForestFloor
                        handleArrayUpload("SoilForestFloor", soilHeaderJSON);

                        // SoilGroundCover
                        handleArrayUpload("SoilGroundCover", soilHeaderJSON);
                    }

                    // SelfQAHeader
                    JSONObject selfQAHeaderJSON = (JSONObject) key.get("tblSelfQAHeader");
                    if (selfQAHeaderJSON != null && selfQAHeaderJSON.size() > 0) {
                        // SelfQAHeader fields
                        JSONArray fields = (JSONArray) selfQAHeaderJSON.get("fields");
                        DatabaseHelper.uploadTableData("tblSelfQAHeader", fields);

                        // SelfQAHt
                        handleArrayUpload("SelfQAHt", selfQAHeaderJSON);

                        // SelfQATree
                        JSONArray selfQATree = (JSONArray) ageHeaderJSON.get("tblSelfQATree");
                        if (selfQATree != null && !selfQATree.isEmpty()) {
                            Iterator<JSONObject> selfQATreeKeys = selfQATree.iterator();
                            while(selfQATreeKeys.hasNext()) {
                                // AgeTree fields
                                JSONObject selfQATreeKey = (JSONObject) selfQATreeKeys.next();
                                JSONArray selfQATreeFields = (JSONArray) selfQATreeKey.get("fields");
                                DatabaseHelper.uploadTableData("tblSelfQATree", selfQATreeFields);

                                // SelfQADeform
                                handleArrayUpload("SelfQADeform", selfQATreeKey);
                            }
                        }
                    }
                }
            }

            // SpecGYHeader
            JSONObject specGYHeaderJSON = (JSONObject) json.get("tblSpecGYHeader");
            if (specGYHeaderJSON != null && specGYHeaderJSON.size() > 0) {
                JSONArray specGYHeaderFields = (JSONArray) specGYHeaderJSON.get("fields");
                DatabaseHelper.uploadTableData("tblSpecGYHeader", specGYHeaderFields);
            }
        }

        // Delay TODO
        TimeUnit.SECONDS.sleep((long) 3.4);
    }

    public void handleUpload(String name, JSONObject refJSON) {
        JSONObject json = (JSONObject) refJSON.get("tbl" + name);
        if (json != null && json.size() > 0) {
            JSONArray fields = (JSONArray) json.get("fields");
            DatabaseHelper.uploadTableData("tbl" + name, fields);
        }
    }

    public void handleArrayUpload(String name, JSONObject refJSON) {
        JSONArray json = (JSONArray) refJSON.get("tbl" + name);
        if (json != null && !json.isEmpty()) {
            Iterator<JSONObject> keys = json.iterator();
            while(keys.hasNext()) {
                JSONObject key = (JSONObject) keys.next();
                JSONArray fields = (JSONArray) key.get("fields");

                DatabaseHelper.uploadTableData("tbl"+ name, fields);
            }
        }
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

    Service exportService = new Service() {

        @Override
        protected Task createTask() {
            Task exportTask = new Task() {
                @Override
                protected Void call() throws Exception {
                    try {
                        // TODO check if the list is empty, and say no exports occurred rather than "success"
                        // Create a new timestamped folder for the exported packages.
                        Path path = Paths.get(System.getProperty("user.dir") + "/exports/" + LocalDate.now() + "-" + System.currentTimeMillis());
                        exportPath = path;
                        Files.createDirectory(path);

                        // Progress
                        System.out.println(listExports.getItems().size());
                        int numOfPackages = listExports.getItems().size();
                        // reset the progress bar and set text.
                        updateProgress(0, numOfPackages);
                        updateMessage("Exporting Plot Packages...");

                        int currentPackage = 0;
                        // Iterate through each package in the exports list.
                        for (Package pkg: listExports.getItems()) {
                            currentPackage++;
                            // Retrieve all data for the plot package from the database
                            pkg.loadPackage();

                            // Update the progress bar.
                            updateMessage("Generating " + pkg.getPlot().getFromFields("PlotName") + "...");
                            updateProgress(currentPackage- 0.5, numOfPackages);

                            JSONObject pkgJSON = pkg.getJSON();

                            updateMessage("Exporting " + pkg.getPlot().getFromFields("PlotName") + "...");

                            // Create JSON file
                            FileWriter file = new FileWriter(path + "/" + pkg.getPlot().getFromFields("PlotName") + ".json");
                            file.write(pkgJSON.toJSONString());
                            file.close();

                            // Update the progress bar.
                            updateProgress(currentPackage, numOfPackages);
                        }
                        updateMessage("Done");
                    } catch (IOException e) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("Unable to create new exports directory.");
//                alert.showAndWait();
                    } catch (Exception e) {
                        e.printStackTrace();
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText(null);
//                alert.setContentText("An unexpected error has occurred. Unable to export plot packages.\n\n"+e.getMessage());
//                alert.showAndWait();
                    }
                    return null;
                }
            };

            exportTask.setOnSucceeded((event -> {
                // Clear the list
                listExports.getItems().clear();

                btnDownloadPackage.disableProperty().set(false);
                btnAddPackageToExport.disableProperty().set(false);
                btnRemovePackageFromExport.disableProperty().set(false);

                // Notify the user of completion.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success");
                alert.setContentText("Plot Packages exported successfully.");
                alert.showAndWait();

                exportService.reset();
            }));

            progressBar.progressProperty().bind(exportTask.progressProperty());
            lblProgress.textProperty().bind(exportTask.messageProperty());

            return exportTask;
        }
    };

    @FXML
    void exportPlotPackages(ActionEvent e) {
        // If the user has not added a plot package to the export list, do nothing.
        if (listExports.getItems().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("No Packages to Export");
            alert.setContentText("Please add a plot package to the export list and try again.");
            alert.showAndWait();
            return;
        }

        btnDownloadPackage.disableProperty().set(true);
        btnAddPackageToExport.disableProperty().set(true);
        btnRemovePackageFromExport.disableProperty().set(true);

        // Start the export service
        if (exportService.isRunning()) {
            exportService.reset();
            exportService.start();
        } else {
            exportService.restart();
        }
    }

    @FXML
    /** Export Plot Packages
     *      This function will export all plot packages from the exports list and
     *      create individual JSON files for each of them in a contained folder.
     * @param
     */
    void exportPlotPackagesOLD(ActionEvent event) {
        try {
            // TODO check if the list is empty, and say no exports occurred rather than "success"
            // Create a new timestamped folder for the exported packages.
            Path path = Paths.get(System.getProperty("user.dir") + "/exports/" + LocalDate.now() + "-" + System.currentTimeMillis());
            exportPath = path;
            Files.createDirectory(path);

            // Progress
            System.out.println(listExports.getItems().size());
            int numOfPackages = listExports.getItems().size();
            double progressPerPackage = (1/numOfPackages) / 2;
            // reset the progress bar and set text.
            progressUpdater.set(0);
            // TODO updateLabel(lblProgress, "Exporting Plot Packages");

            // Iterate through each package in the exports list.
            for (Package pkg: listExports.getItems()) {

                lblProgress.setText("Generating " + pkg.getPlot().getFromFields("PlotName"));

                // Retrieve all data for the plot package from the database
                pkg.loadPackage();
                // Update the progress bar.
                progressUpdater.set(progressBar.progressProperty().doubleValue() + progressPerPackage);

                JSONObject pkgJSON = pkg.getJSON();

                lblProgress.setText("Exporting " + pkg.getPlot().getFromFields("PlotName"));

                // Create JSON file
                FileWriter file = new FileWriter(path + "/" + pkg.getPlot().getFromFields("PlotName") + ".json");
                file.write(pkgJSON.toJSONString());
                file.close();

                // Update the progress bar.
                progressUpdater.set(progressBar.progressProperty().doubleValue() + progressPerPackage);
            }
            lblProgress.setText("Done");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("All Plot Packages exported successfully.");
            alert.showAndWait();

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
    void importPlotPackage(ActionEvent event) throws IOException, ParseException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Plot Package");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.json")
        );
        File selectedFile = fileChooser.showOpenDialog(Main.getStage());
        if (selectedFile != null) {

            // Copy file to imports folder.
            selectedFile.renameTo(new File(importPath + "/" + selectedFile.getName()));
            // Add the new json file to the imports list.
            loadImportedPackages();
        }
    }

    @FXML
    void clearExportList(ActionEvent event) {
        try {
            listExports.getItems().clear();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Error clearing export list.");
            alert.showAndWait();
        }
    }

    @FXML
    public void quitApplication(ActionEvent event) {
        System.exit(0);
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
